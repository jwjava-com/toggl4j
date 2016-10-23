package org.yaoyao.toggl4j.service;

import org.yaoyao.toggl4j.common.ApiService;
import org.yaoyao.toggl4j.common.OpenAPI;
import org.yaoyao.toggl4j.common.ParamAttr;
import org.yaoyao.toggl4j.entity.Client;

@ApiService
public interface ClientService {

  @OpenAPI(uriPath = "/clients", httpMethod = OpenAPI.HttpMethod.POST)
  Client create(@ParamAttr(location = ParamAttr.Location.PAYLOAD, paramKey = "id") int id,
                @ParamAttr(location = ParamAttr.Location.PAYLOAD, paramKey = "wid") int wid,
                @ParamAttr(location = ParamAttr.Location.PAYLOAD, paramKey = "name") String name,
                @ParamAttr(location = ParamAttr.Location.PAYLOAD, paramKey = "at") String at
                );



}
