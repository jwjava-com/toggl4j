package org.yaoyao.toggl4j.client;

public class ApiResult {
  private int code;
  private Throwable throwable;
  private String jsonBody;

  public ApiResult() {
  }

  public ApiResult(int code, Throwable throwable, String jsonBody) {
    this.code = code;
    this.throwable = throwable;
    this.jsonBody = jsonBody;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public void setThrowable(Throwable throwable) {
    this.throwable = throwable;
  }

  public String getJsonBody() {
    return jsonBody;
  }

  public void setJsonBody(String  jsonBody) {
    this.jsonBody = jsonBody;
  }
}
