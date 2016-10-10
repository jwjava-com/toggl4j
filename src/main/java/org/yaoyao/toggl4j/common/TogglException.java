package org.yaoyao.toggl4j.common;

public class TogglException extends Exception {
  private static final long serialVersionUID = 1L;
  private int code;
  private String message;

  public TogglException(String message, int code) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
