package com.plexus.workflow.domain.features.http;

import jakarta.enterprise.context.ApplicationScoped;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ApplicationScoped
// implement request here
public class Client implements Request {

  private static final Logger log = LogManager.getLogger(Client.class);

  private final HttpClient httpClient;

  public Client(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  /**
   * @param bodyHandler
   * @param uri
   * @param <T>
   * @return CompletableFuture<HttpResponse<T>>
   * @throws Exception
   * @apiNote
   *     <p>The CompletableFuture must be handled in the caller method using .thenApply() which
   *     requires a function to handle the response
   */
  @Override
  public <T> CompletableFuture<HttpResponse<T>> genericGetAsyncObject(
      HttpResponse.BodyHandler<T> bodyHandler, String uri) {
    HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).GET().build();

    //  This only sends a GET request and returns the response as a CompletableFuture
    //  it should be then processed by the caller to extract the body using
    // .thenApply(HttpResponse::body) in the caller method

    return httpClient.sendAsync(httpRequest, bodyHandler);
  }

  @Override
  public <T> CompletableFuture<HttpResponse<T>> genericPostAsyncObject(
      String jsonBody, String uri, HttpResponse.BodyHandler<T> bodyHandler) {
    HttpRequest httpRequest =
        HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
    return httpClient.sendAsync(httpRequest, bodyHandler);
  }

  @Override
  public <T> CompletableFuture<T> headersGetAsyncObject(
      HttpResponse<T> response, String uri, Map<String, String> headers) {
    return null;
  }

  @Override
  public <T> CompletableFuture<T> headersPostAsyncObject(
      String jsonBody, String uri, Map<String, String> headers, HttpResponse<T> response)
      throws Exception {
    return null;
  }
}
