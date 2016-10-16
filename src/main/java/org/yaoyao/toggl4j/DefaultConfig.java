package org.yaoyao.toggl4j;


import java.util.Map;
import java.util.Objects;

/**
 * Default config for the project. Include these props:
 * apiDomain:  https://www.toggl.com/api/v8
 * globalHttpConfig: default config for the <code>DefaultAsyncHttpConfig.Builder</code>
 * to build the client. props as show below:
 *      requestTimeout = 6000;
 *      readTimeout = 3000;
 *      maxRequestRetry = 0;
 *      connectTimeout = 5000;
 * emh... nothing matters. config it as you like use the <code>customHttpConfig</code>.
 * <strong>user</strong> and <strong>password</strong> must be set to init the defaultConfig so that
 * you get authorized.
 */
public class DefaultConfig {
  // TODO: 2016/10/15 Global authentication? use Oauth Object or something else？
  private String apiDomain = "https://www.toggl.com/api/v8";
  private DefaultConfig.HttpConfig globalHttpConfig = new DefaultConfig.HttpConfig();
  private Map<String, HttpConfig> customHttpConfig;

  private String user;
  private String password;

  public DefaultConfig(String user, String password) {
    Objects.requireNonNull(user, "user required.");
    Objects.requireNonNull(password, "password required");
    this.user = user;
    this.password = password;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

  public String getApiDomain() {
    return apiDomain;
  }

  public void setApiDomain(String apiDomain) {
    this.apiDomain = apiDomain;
  }

  public HttpConfig getGlobalHttpConfig() {
    return globalHttpConfig;
  }

  public void setGlobalHttpConfig(HttpConfig globalHttpConfig) {
    this.globalHttpConfig = globalHttpConfig;
  }

  public Map<String, HttpConfig> getCustomHttpConfig() {
    return customHttpConfig;
  }

  public void setCustomHttpConfig(Map<String, HttpConfig> customHttpConfig) {
    this.customHttpConfig = customHttpConfig;
  }

  public static class HttpConfig {
    private int requestTimeout = 6000;
    private int readTimeout = 3000;
    private int maxRequestRetry = 0;
    private int connectTimeout = 5000;

    public HttpConfig() {
      super();
    }

    public int getRequestTimeout() {
      return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
      this.requestTimeout = requestTimeout;
    }

    public int getReadTimeout() {
      return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
      this.readTimeout = readTimeout;
    }

    public int getMaxRequestRetry() {
      return maxRequestRetry;
    }

    public void setMaxRequestRetry(int maxRequestRetry) {
      this.maxRequestRetry = maxRequestRetry;
    }

    public int getConnectTimeout() {
      return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
      this.connectTimeout = connectTimeout;
    }
  }
}
