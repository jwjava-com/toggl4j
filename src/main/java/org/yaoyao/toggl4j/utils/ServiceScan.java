package org.yaoyao.toggl4j.utils;

import org.reflections.Reflections;
import org.yaoyao.toggl4j.common.ApiService;

import java.util.Set;

public final class ServiceScan {
    public static Set<Class<?>> scan(String packagePath) {
        Reflections reflections = new Reflections(packagePath);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ApiService.class);
        return classes;
    }
    //test
    public static void main(String[] args) {
        System.out.println(ServiceScan.scan("org.yaoyao.toggl4j.service"));
    }
}
