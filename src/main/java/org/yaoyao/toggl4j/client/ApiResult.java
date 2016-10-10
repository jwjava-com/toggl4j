package org.yaoyao.toggl4j.client;

import java.util.Map;

public class ApiResult {
  private int code;
  private String msg;

  public ApiResult() {
  }

  public ApiResult(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
