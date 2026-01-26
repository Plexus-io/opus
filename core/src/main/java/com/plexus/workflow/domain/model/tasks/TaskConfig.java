package com.plexus.workflow.domain.model.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;

public class TaskConfig {
  private Request request;

  //  Requires changes when converted into JAR for docker deployment
  //  Possible annotation to be added @RegisterForReflection for Jackson reflection by JVM
  public static class Request {
    private String method;
    private String url;
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private JsonNode body;

    public String getMethod() {
      return method;
    }

    public String getUrl() {
      return url;
    }

    public Map<String, String> getHeaders() {
      return headers;
    }

    public Map<String, String> getQueryParams() {
      return queryParams;
    }

    public JsonNode getBody() {
      return body;
    }

    public void setMethod(String method) {
      this.method = method;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public void setHeaders(Map<String, String> headers) {
      this.headers = headers;
    }

    public void setQueryParams(Map<String, String> queryParams) {
      this.queryParams = queryParams;
    }

    public void setBody(JsonNode body) {
      this.body = body;
    }
  }

  public Request getRequest() {
    return request;
  }

  public void setRequest(Request request) {
    this.request = request;
  }
}
