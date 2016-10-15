package org.yaoyao.toggl4j.client;

import org.yaoyao.toggl4j.common.OpenAPI.HttpMethod;

import java.util.List;

import static org.yaoyao.toggl4j.common.ParamAttr.*;

public class ApiAttr {
  private String url;
  private HttpMethod httpMethod;
  private List<ApiParam> apiParams;
  private ApiAttr.ReturnClass returnClass;
  private String resultJsonKey;

  public ApiAttr() {
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public HttpMethod getHttpMethod() {
    return this.httpMethod;
  }

  public void setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
  }

  public List<ApiAttr.ApiParam> getApiParams() {
    return this.apiParams;
  }

  public void setApiParams(List<ApiAttr.ApiParam> apiParams) {
    this.apiParams = apiParams;
  }

  public ApiAttr.ReturnClass getReturnClass() {
    return this.returnClass;
  }

  public void setReturnClass(ApiAttr.ReturnClass returnClass) {
    this.returnClass = returnClass;
  }

  public String getResultJsonKey() {
    return this.resultJsonKey;
  }

  public void setResultJsonKey(String resultJsonKey) {
    this.resultJsonKey = resultJsonKey;
  }


  public static class ReturnClass {
    private boolean isList;
    private Class<?> clazz;

    public ReturnClass() {
    }

    public boolean isList() {
      return this.isList;
    }

    public void setList(boolean isList) {
      this.isList = isList;
    }

    public Class<?> getClazz() {
      return this.clazz;
    }

    public void setClazz(Class<?> clazz) {
      this.clazz = clazz;
    }
  }

  public static class ApiParam {
    private int index;
    private Location location;
    private String key;

    public ApiParam() {
    }

    public int getIndex() {
      return this.index;
    }

    public void setIndex(int index) {
      this.index = index;
    }

    public Location getLocation() {
      return this.location;
    }

    public void setLocation(Location location) {
      this.location = location;
    }

    public String getKey() {
      return this.key;
    }

    public void setKey(String key) {
      this.key = key;
    }
  }
}
