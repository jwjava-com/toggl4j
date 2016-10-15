package org.yaoyao.toggl4j.client;

import java.util.Map;

public class RequestData {
  private ApiAttr apiAttr;
  private String fullUrl;
  private String requestId;
  private String finalPostFileKey;
  private Map<String, Object> postObj;
  private String apiInfo;

  public RequestData() {
  }

  public ApiAttr getApiAttr() {
    return this.apiAttr;
  }

  public void setApiAttr(ApiAttr apiAttr) {
    this.apiAttr = apiAttr;
  }

  public String getFullUrl() {
    return this.fullUrl;
  }

  public void setFullUrl(String fullUrl) {
    this.fullUrl = fullUrl;
  }

  public String getRequestId() {
    return this.requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getFinalPostFileKey() {
    return this.finalPostFileKey;
  }

  public void setFinalPostFileKey(String finalPostFileKey) {
    this.finalPostFileKey = finalPostFileKey;
  }


  public Map<String, Object> getPostObj() {
    return this.postObj;
  }

  public void setPostObj(Map<String, Object> postObj) {
    this.postObj = postObj;
  }

  public String getApiInfo() {
    return this.apiInfo;
  }

  public void setApiInfo(String apiInfo) {
    this.apiInfo = apiInfo;
  }
}
