package org.yaoyao.toggl4j.service;

import com.alibaba.fastjson.JSONObject;
import org.yaoyao.toggl4j.common.ApiService;
import org.yaoyao.toggl4j.common.OpenAPI;
import org.yaoyao.toggl4j.common.ParamAttr;
import org.yaoyao.toggl4j.entity.Client;
import org.yaoyao.toggl4j.entity.TogglResponse;

@ApiService
public interface ClientService {

  @OpenAPI(uriPath = "/clients", httpMethod = OpenAPI.HttpMethod.POST)
  JSONObject create(@ParamAttr(location = ParamAttr.Location.PAYLOAD, paramKey = "") Client client);



}
