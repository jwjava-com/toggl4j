package org.yaoyao.toggl4j.client;

import org.junit.Test;
import org.yaoyao.toggl4j.DefaultConfig;
import org.yaoyao.toggl4j.common.OpenAPI;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class AsyncHttpInvokerTest {
  RequestData r = new RequestData();
  DefaultConfig defaultConfig = new DefaultConfig("677c928fbeb8e8be02ab68d0a2bca359", "api_token");
  AsyncHttpInvoker asyncHttpInvoker = new AsyncHttpInvoker(defaultConfig);
  Map<String, Object> map = new HashMap<String, Object>();
  ApiAttr apiAttr = new ApiAttr();

  @Test
  public void execute() throws Exception {
    apiAttr.setHttpMethod(OpenAPI.HttpMethod.GET);
    r.setFullUrl("https://www.toggl.com/api/v8/me");
    r.setApiAttr(apiAttr);
    ApiResult result = asyncHttpInvoker.execute(r);
    System.out.println(result.getCode());
    System.out.println(result.getJsonBody());
  }

  @Test
  public void close() throws Exception {

  }

}
