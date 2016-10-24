package org.yaoyao.toggl4j.entity;

import java.util.Map;

public class TogglResponse {
  private Map<String, Object> data;

  public TogglResponse() {
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }
}
