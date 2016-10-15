package org.yaoyao.toggl4j.client;

import com.alibaba.fastjson.JSON;
import org.asynchttpclient.*;
import org.yaoyao.toggl4j.DefaultConfig;
import org.yaoyao.toggl4j.common.OpenAPI;

import java.util.Map;
import java.util.Optional;

import static org.asynchttpclient.DefaultAsyncHttpClientConfig.*;
import static org.yaoyao.toggl4j.DefaultConfig.*;

public class AsyncHttpInvoker implements HttpInvoker {
  // TODO: 2016/10/15 add log (logback)
  private AsyncHttpClient asyncHttpClient;
  private String user;
  private String password;

  // init --> use Default config
  public AsyncHttpInvoker(DefaultConfig defaultConfig) {
    Builder builder = new Builder();
    HttpConfig httpConfig = defaultConfig.getGlobalHttpConfig();
    builder.setMaxRequestRetry(httpConfig.getMaxRequestRetry());
    builder.setConnectTimeout(httpConfig.getConnectTimeout());
    builder.setRequestTimeout(httpConfig.getRequestTimeout());
    builder.setReadTimeout(httpConfig.getReadTimeout());
    this.asyncHttpClient = new DefaultAsyncHttpClient(builder.build());
    this.user = defaultConfig.getUser();
    this.password = defaultConfig.getPassword();
  }

  @Override
  public ApiResult execute(RequestData requestData) throws Exception {
    // get things ready from requestData to build a Request for the asynchttpclient.
    ApiResult apiResult = null;
    ApiAttr apiAttr = requestData.getApiAttr();
    String fullUrl = requestData.getFullUrl();
    Map<String, Object> postObj = requestData.getPostObj();
    OpenAPI.HttpMethod httpMethod = apiAttr.getHttpMethod();
    // build
    Request request = buildRequest(httpMethod, fullUrl, postObj).get();
    this.asyncHttpClient.executeRequest(request)
                        .toCompletableFuture()
                        .exceptionally(t -> null) // TODO: 2016/10/15 exception handler
                        .thenApply(response -> null) // TODO: 2016/10/15 response handler
                        .join();

    return apiResult;
  }

  private Optional<Request> buildRequest(OpenAPI.HttpMethod httpMethod, String fullUrl, Map<String, Object> postObj) {
    RequestBuilder rqBuiler = new RequestBuilder();
    rqBuiler.setHeader(user, password);
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

  }
}
