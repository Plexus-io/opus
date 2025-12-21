package com.plexus.api.domain;

public class GenericResponse<T> {
  private T data;
  private String requestId;
  private String response;

  public GenericResponse(T data, String requestId, String response) {
    this.data = data;
    this.requestId = requestId;
    this.response = response;
  }

  public T getData() {
    return data;
  }

  public String getRequestId() {
    return requestId;
  }

  public String getResponse() {
    return response;
  }

  public void setData(T data) {
    this.data = data;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public void setResponse(String response) {
    this.response = response;
  }
}
