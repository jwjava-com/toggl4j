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

  enum HttpMethod {
    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    DELETE("DELETE");
    private String name;
    public String getName() {
      return name;
    }
    HttpMethod (String name) {
      this.name = name;
    }
  }

}
