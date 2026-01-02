package com.plexus.workflow.domain.features.http;

import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface Request {

  //    Methods to be implemented in Client HTTP requests
  //          1. ValidateURL (i'm trying to keep this on the frontend side)
  //          2. SetHeaders (Map<String, String> headers) age, expiry, content-type etc
  //          3. Method (HTTPMethod method) GET, POST, PUT, DELETE. By Default GET

  /**
   * @param response
   * @param uri
   * @return CompletableFuture<T>
   */

  // methods
  //      Or use HTTPRequest.BodyPublisher for jsonBody

  <T> CompletableFuture<HttpResponse<T>> genericGetAsyncObject(
      HttpResponse.BodyHandler<T> bodyHandler, String uri) throws Exception;

  <T> CompletableFuture<HttpResponse<T>> genericPostAsyncObject(
      String jsonBody, String uri, HttpResponse.BodyHandler<T> bodyHandler) throws Exception;

  <T> CompletableFuture<T> headersGetAsyncObject(
      HttpResponse<T> response, String uri, Map<String, String> headers) throws Exception;

  <T> CompletableFuture<T> headersPostAsyncObject(
      String jsonBody, String uri, Map<String, String> headers, HttpResponse<T> response)
      throws Exception;
}
