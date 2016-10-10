package org.yaoyao.toggl4j.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.TYPE})
public @interface ParamAttr {
  String paramKey();
  Location location();

  public static enum Location {
    URL,
    PAYLOAD;
    private Location() {

    }
  }
}
