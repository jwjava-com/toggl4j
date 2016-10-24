package org.yaoyao.toggl4j;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.yaoyao.toggl4j.client.ApiAttr;
import org.yaoyao.toggl4j.client.AsyncHttpInvoker;
import org.yaoyao.toggl4j.client.RequestData;
import org.yaoyao.toggl4j.entity.Client;
import org.yaoyao.toggl4j.entity.TogglResponse;
import org.yaoyao.toggl4j.service.ClientService;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by 73528_000 on 2016/10/23.
 */
public class ServiceProxyFactoryTest {

  DefaultConfig defaultConfig = new DefaultConfig("677c928fbeb8e8be02ab68d0a2bca359", "api_token");

  @Test
  public void newInstance() throws Exception {
    Client client = new Client();
    client.setName("hello");

    JSONObject response  =
        ServiceProxyFactory.newInstance(defaultConfig).getTogglService(ClientService.class).create(client);
    System.out.println(client);
  }

  @Test
  public void testDate() {
    System.out.println(Instant.now());
  }
}
