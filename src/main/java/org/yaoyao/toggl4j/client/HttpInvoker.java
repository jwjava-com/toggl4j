package org.yaoyao.toggl4j.client;

public interface HttpInvoker {

  /**
   * Do the http request.
   *
   * @param requestData
   * @return
   * @throws Exception
   */
  ApiResult execute(RequestData requestData) throws Exception;

  /**
   * release connection
   */
  void close();

}
