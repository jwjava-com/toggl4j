package org.yaoyao.toggl4j.client;

import org.yaoyao.toggl4j.DefaultConfig;
import org.yaoyao.toggl4j.common.OpenAPI;
import org.yaoyao.toggl4j.common.ParamAttr;
import org.yaoyao.toggl4j.common.ParamAttr.Location;
import org.yaoyao.toggl4j.common.ServiceInitException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

import static org.yaoyao.toggl4j.client.ApiAttr.*;
import static org.yaoyao.toggl4j.client.ApiAttr.ApiParam;

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
    return null;
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

    for (int i = 0; i < method.getParameterAnnotations().length; i++) {
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
        this.initApiParams(apiAttr, method, clazz);
        this.initReturnClass(apiAttr, method, clazz);
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
