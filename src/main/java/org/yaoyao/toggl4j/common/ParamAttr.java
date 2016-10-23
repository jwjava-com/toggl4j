package org.yaoyao.toggl4j.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
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
