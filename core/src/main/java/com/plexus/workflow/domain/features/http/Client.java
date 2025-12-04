package com.plexus.workflow.domain.features.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client implements Request {

  private static final Logger log = LogManager.getLogger(Client.class);

  private final URI uri;
  private final HttpClient httpClient;
  private final HttpRequest httpRequest;

  public Client(URI uri, HttpClient httpClient, HttpRequest httpRequest) {
    this.uri = uri;
    this.httpClient = httpClient;
    this.httpRequest = httpRequest;
  }

  //    TODO
  //      Change the return type into a class that you require this is from gpt as an example
  //          also check how to handle exceptions for various status codes
  @Override
  public <T> CompletableFuture<T> asyncObject(Class<T> type, int code) {
    return httpClient
        .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenApply(json -> fromJson(json, type));
  }

  @Override
  public <T> CompletableFuture<T> asyncObject(Class<T> type) {
    return null;
  }

  //    TODO
  //      Change this into a class that you require this is from gpt as an example

  private <T> T fromJson(String json, Class<T> type) {
    try {
      return new ObjectMapper().readValue(json, type);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
