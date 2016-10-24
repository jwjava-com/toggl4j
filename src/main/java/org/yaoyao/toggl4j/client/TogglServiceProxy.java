package org.yaoyao.toggl4j.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.yaoyao.toggl4j.DefaultConfig;
import org.yaoyao.toggl4j.common.OpenAPI;
import org.yaoyao.toggl4j.common.ParamAttr;
import org.yaoyao.toggl4j.common.ParamAttr.Location;
import org.yaoyao.toggl4j.common.ServiceInitException;
import org.yaoyao.toggl4j.common.TogglServiceException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

import static org.yaoyao.toggl4j.client.ApiAttr.ApiParam;
import static org.yaoyao.toggl4j.client.ApiAttr.ReturnClass;

/**
 * httpInvoker proxy.
 */
public class TogglServiceProxy implements InvocationHandler {
  private Map<String, ApiAttr> apiAttrMap;
  private DefaultConfig defaultConfig;
  private HttpInvoker httpInvoker;

  public TogglServiceProxy(DefaultConfig defaultConfig, HttpInvoker httpInvoker, Set<Class<?>> apiServices) throws ServiceInitException {
    if (apiServices.isEmpty()) {
      throw new ServiceInitException("Service init failed... No Services Available");
    }
    this.defaultConfig = defaultConfig;
    this.httpInvoker = httpInvoker;
    this.apiAttrMap = new HashMap<>();
    this.parseApi(defaultConfig.getApiDomain(), apiServices);
    this.checkCustomHttpConfig();
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    String fullUrl = "";
    String api = method.getDeclaringClass().getSimpleName() + "." + method.getName();
    int httpCode = 0;
    String responseJson = "";
    OpenAPI openAPI = method.getAnnotation(OpenAPI.class);
    if (openAPI == null) {
      throw new ServiceInitException("Service init failed... [" + api
              + "] need OpenAPI annotation");
    }
    if (isEmpty(openAPI.uriPath())) {
      throw new ServiceInitException("Service init failed... [" +  api
              + "] need uriPath");
    }
    ApiAttr apiAttr = this.apiAttrMap.get(openAPI.uriPath());
    if (apiAttr == null) {
      throw new ServiceInitException("Service init failed... [" +  openAPI.uriPath()
              + "] init failed");
    }
    if (apiAttr.getApiParams().size() != args.length) {
      throw new ServiceInitException("Service init failed... [" + api
              + "] param size does not fit");
    }

    StringBuilder urlParam = new StringBuilder("?");
    int urlParamNum = 0;
    int argIndex = 0;
    final Map<String, Object> payload = new HashMap();
    Object jsonObject = null;
    while (true) {
      if (argIndex >= apiAttr.getApiParams().size()) {
        fullUrl = urlParam.insert(0, apiAttr.getUrl()).toString();
        Object postObject = jsonObject == null ? payload : jsonObject;
        RequestData requestData = new RequestData();
        requestData.setFullUrl(fullUrl);
        requestData.setApiAttr(apiAttr);
        requestData.setApiInfo(api);
        requestData.setPostObj(payload);
        ApiResult result = this.httpInvoker.execute(requestData);
        if (result == null) {
          throw new Exception("unknown exception happened. get in touch with yaoyao.");
        }
        if (result.getThrowable() != null) {
          throw result.getThrowable();
        }
        httpCode = result.getCode();
        responseJson = result.getJsonBody();

        if (httpCode != 200) {
          throw new TogglServiceException("error response: " + responseJson) ;
        }

        JSONObject resultAsJson = JSON.parseObject(responseJson);
        if (!Void.class.equals(apiAttr.getReturnClass().getClazz()) &&
            !Void.TYPE.equals(apiAttr.getReturnClass().getClazz())) {
          if (apiAttr.getReturnClass().isList()) {
            return JSON.parseArray(responseJson, apiAttr.getReturnClass().getClazz());
          } else {
//            return JSON.parseObject(responseJson, apiAttr.getReturnClass().getClazz());
            return resultAsJson;
          }
        }
      }

      ApiParam params = apiAttr.getApiParams().get(argIndex);
      // build query param
      if (params.getLocation() == Location.URL) {
        if (args[argIndex] != null) {
          ++urlParamNum;
          if (urlParamNum > 1) {
            urlParam.append("&");
          }
          urlParam.append(params.getKey());
          urlParam.append("=");
          urlParam.append(args[argIndex]);
        }
      } else {
        if (isEmpty(params.getKey())) {
          jsonObject = args[argIndex];
        } else {
         payload.put(params.getKey(), args[argIndex]);
        }
      }
      ++argIndex;
    }
  }

  // ensure apidomain ends with "/" , uri path starts with "/"
  private void initFullUrl(ApiAttr apiAttr, Method method, OpenAPI openAPI, String apiDomain) {
    String fullUrl = apiDomain.endsWith("/") ?
        apiDomain.substring(0, apiDomain.length() - 1) :
        apiDomain + (openAPI.uriPath().startsWith("/") ?
            openAPI.uriPath() :
            "/" + openAPI.uriPath());
    apiAttr.setUrl(fullUrl);
  }

  private void initApiParams(ApiAttr apiAttr, Method method, Class<?> clazz) throws ServiceInitException {
    List<ApiParam> apiParams = new ArrayList();
    int emptyParamKeyTotal = 0;

    for (int i = 0; i < method.getParameterAnnotations().length; ++i) {
      Annotation[] paramAnnotations = method.getParameterAnnotations()[i];
      if (paramAnnotations.length == 0) {
        throw new ServiceInitException(
            "Service init failed... [" + clazz.getCanonicalName() + "." + method.getName()
                + "] need ParamAttr annotation");
      }
      ApiParam apiParam = new ApiParam();
      apiParam.setIndex(i);
      boolean hasParamAttr = false;
      for (Annotation annotation : paramAnnotations) {
        if (annotation instanceof ParamAttr) {
          ParamAttr paramAttr = (ParamAttr) annotation;
          // empty param key  handle
          if (paramAttr.location() == Location.URL && isEmpty(paramAttr.paramKey())) {
            throw new ServiceInitException(
                "Service init failed... [" + clazz.getCanonicalName() + "." + method.getName()
                    + "] key have to be set when use Location.URL");
          } else {
            if (paramAttr.location() == Location.PAYLOAD && isEmpty(paramAttr.paramKey())) {
              emptyParamKeyTotal++;
            }
          }

          apiParam.setKey(paramAttr.paramKey());
          apiParam.setLocation(paramAttr.location());
          hasParamAttr = true;
          break;
        }
      }

      if (!hasParamAttr) {
        throw new ServiceInitException(
            "Service init failed... [" + clazz.getCanonicalName() + "." + method.getName()
                + "] need ParamAttr annotation");
      }

      if (emptyParamKeyTotal > 1) {
        throw new ServiceInitException(
            "Service init failed... [" + clazz.getCanonicalName() + "." + method.getName()
                + "] only one empty key permitted");
      }

      apiParams.add(apiParam);
    }
    apiAttr.setApiParams(apiParams);
  }

  private void initReturnClass(ApiAttr apiAttr, Method method, Class<?> clazz) throws ServiceInitException {
    ReturnClass returnClass = new ReturnClass();
    Type genericReturnType = method.getGenericReturnType();
    if (ParameterizedType.class.isAssignableFrom(genericReturnType.getClass())) {
      Type rawType = ((ParameterizedType)genericReturnType).getRawType();
      if (!List.class.equals(rawType)) {
        throw new ServiceInitException("Service init failed... ["
            + clazz.getCanonicalName() + "." + method.getName() +"] now only supports List<T>");
      }
      Type argumentsType = ((ParameterizedType)genericReturnType).getActualTypeArguments()[0];
      returnClass.setList(true);
      returnClass.setClazz((Class<?>) argumentsType);
    } else {
      returnClass.setList(false);
      returnClass.setClazz((Class<?>) genericReturnType);
    }
    apiAttr.setReturnClass(returnClass);
  }

  // interface reflect to api.
  private void parseApi(String apiDomain, Set<Class<?>> apiSerivices) throws ServiceInitException {
    for (Class clazz : apiSerivices) {
      Method[] methods = clazz.getMethods();
      for (Method method : methods) {
        ApiAttr apiAttr = new ApiAttr();
        OpenAPI openAPI = method.getAnnotation(OpenAPI.class);
        if (this.apiAttrMap.get(openAPI.uriPath()) != null) {
          throw new ServiceInitException("Service init failed... ["
              + clazz.getCanonicalName() + "." + method.getName()  +"]" + openAPI.uriPath() + "duplicated in OpenAPI annotation");
        }
        this.initFullUrl(apiAttr, method, openAPI, apiDomain);
        apiAttr.setHttpMethod(openAPI.httpMethod());
        this.initReturnClass(apiAttr, method, clazz);
        this.initApiParams(apiAttr, method, clazz);
        this.apiAttrMap.put(openAPI.uriPath(), apiAttr);
      }


    }
  }
  private boolean isEmpty(String str) {
    return str == null || str.isEmpty();
  }



  private void checkCustomHttpConfig() throws ServiceInitException {
    if (this.defaultConfig.getCustomHttpConfig() != null) {
      for (String key : defaultConfig.getCustomHttpConfig().keySet()) {
        if (!apiAttrMap.containsKey(key)) {
          throw new ServiceInitException("Service init failed..." + key + "does not exists");
        }
      }
    }
  }


}
