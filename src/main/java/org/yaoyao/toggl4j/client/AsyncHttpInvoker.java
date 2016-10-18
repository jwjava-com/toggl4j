package org.yaoyao.toggl4j.client;

import com.alibaba.fastjson.JSON;
import org.asynchttpclient.*;
import org.yaoyao.toggl4j.DefaultConfig;
import org.yaoyao.toggl4j.common.OpenAPI;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.asynchttpclient.DefaultAsyncHttpClientConfig.*;
import static org.yaoyao.toggl4j.DefaultConfig.*;

public class AsyncHttpInvoker implements HttpInvoker {
  // TODO: 2016/10/15 add log (logback)
  private AsyncHttpClient asyncHttpClient;
  private String user;
  private String password;

  // init --> use Default config
  public AsyncHttpInvoker(DefaultConfig defaultConfig) {
    this.user = defaultConfig.getUser();
    this.password = defaultConfig.getPassword();
    // http basic auth
    Realm realm = new Realm.Builder(user, password)
        .setScheme(Realm.AuthScheme.BASIC)
        .setUsePreemptiveAuth(true)
        .build();
    Builder builder = new Builder();
    HttpConfig httpConfig = defaultConfig.getGlobalHttpConfig();
    builder.setMaxRequestRetry(httpConfig.getMaxRequestRetry());
    builder.setConnectTimeout(httpConfig.getConnectTimeout());
    builder.setRequestTimeout(httpConfig.getRequestTimeout());
    builder.setReadTimeout(httpConfig.getReadTimeout());
    builder.setRealm(realm);
    this.asyncHttpClient = new DefaultAsyncHttpClient(builder.build());

  }

  @Override
  public ApiResult execute(RequestData requestData) throws Exception {
    // get things ready from requestData to build a Request for the asynchttpclient.
    final ApiResult apiResult = new ApiResult();
    ApiAttr apiAttr = requestData.getApiAttr();
    String fullUrl = requestData.getFullUrl();
    Map<String, Object> postObj = requestData.getPostObj();
    OpenAPI.HttpMethod httpMethod = apiAttr.getHttpMethod();
    // build
    Optional<Request> request = buildRequest(httpMethod, fullUrl, postObj);
    this.asyncHttpClient.executeRequest(request.isPresent() ? request.get() : null)
        .toCompletableFuture()
        .thenAccept(response -> {
          apiResult.setCode(response.getStatusCode());
          apiResult.setJsonBody(response.getResponseBody(Charset.forName("UTF-8")));
        })
        .exceptionally(t -> {
          apiResult.setThrowable(t);
          return null;
        })
        .join();

    return apiResult;
  }

  private Optional<Request> buildRequest(OpenAPI.HttpMethod httpMethod, String fullUrl, Map<String, Object> postObj) {
    RequestBuilder rqBuiler = new RequestBuilder();
    rqBuiler.setUrl(fullUrl);
    switch (httpMethod) {
      case POST: {
        rqBuiler.setMethod(httpMethod.getName());
        rqBuiler.setBody(JSON.toJSONString(postObj));
        break;
      }
      // besides POST
      default: {
        rqBuiler.setMethod(httpMethod.getName());
      }
    }
    return Optional.ofNullable(rqBuiler.build());
  }


  @Override
  public void close() {
    try {
      this.asyncHttpClient.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
