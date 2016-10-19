package org.yaoyao.toggl4j.service;

import org.yaoyao.toggl4j.common.ApiService;
import org.yaoyao.toggl4j.common.OpenAPI;
import org.yaoyao.toggl4j.common.ParamAttr;

import static org.yaoyao.toggl4j.common.ParamAttr.Location.*;

@ApiService
public interface ClientService {

  @OpenAPI(uriPath = "/clients", httpMethod = OpenAPI.HttpMethod.POST)
  void createClient(@ParamAttr(paramKey = "name", location = PAYLOAD) String name,
                    @ParamAttr(paramKey = "wid", location = PAYLOAD) long wid,
                    @ParamAttr(paramKey = "notes", location = PAYLOAD) String notes,
                    @ParamAttr(paramKey = "at", location = PAYLOAD) String at);

}
