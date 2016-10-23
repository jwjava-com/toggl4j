package org.yaoyao.toggl4j;

import org.yaoyao.toggl4j.client.AsyncHttpInvoker;
import org.yaoyao.toggl4j.client.HttpInvoker;
import org.yaoyao.toggl4j.client.TogglServiceProxy;
import org.yaoyao.toggl4j.common.ServiceInitException;
import org.yaoyao.toggl4j.utils.ServiceScan;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.Set;

/**
 * Factory to gen service proxy
 */
public class ServiceProxyFactory implements Closeable {

  private static ServiceProxyFactory factory;
  private Object serviceProxy;
  private Set<Class<?>> apiServices;
  private HttpInvoker httpInvoker;

  private ServiceProxyFactory(DefaultConfig defaultConfig) throws ServiceInitException {
    this.apiServices = scanService(defaultConfig.getBasePackage());
    if (apiServices.isEmpty()) {
      throw new ServiceInitException("service init failed... cant find api under the package "
          + defaultConfig.getBasePackage());
    } else {
      Class[] services = apiServices.toArray(new Class[apiServices.size()]);
      httpInvoker = new AsyncHttpInvoker(defaultConfig);
      this.serviceProxy = Proxy.newProxyInstance(ServiceProxyFactory.class.getClassLoader(),
          services, new TogglServiceProxy(defaultConfig, httpInvoker, apiServices));
    }
  }

  public <T> T getTogglService(Class<T> clazz) {
    if (!this.apiServices.contains(clazz)) {
      throw new IllegalArgumentException("Service unSupported");
    }
    return (T) this.serviceProxy;
  }

  public static final synchronized ServiceProxyFactory newInstance(DefaultConfig defaultConfig) throws ServiceInitException {
    Objects.nonNull(defaultConfig);
    if (factory == null) {
      factory = new ServiceProxyFactory(defaultConfig);
    }
    return factory;
  }

  private Set<Class<?>> scanService(String basePackage) {
   return  ServiceScan.scan(basePackage);
  }


  @Override
  public void close() throws IOException {
    this.httpInvoker.close();
  }


}
