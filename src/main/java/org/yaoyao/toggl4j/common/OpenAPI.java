package org.yaoyao.toggl4j.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenAPI {
  String uriPath();
  String resultJsonKey() default "";
  HttpMethod httpMethod();

  public static enum HttpMethod {
    POST,
    GET,
    PUT,
    DELETE;

    private HttpMethod() {

    }
  }

}
