package com.plexus.workflow.domain.features.http;

import java.util.concurrent.CompletableFuture;

public interface Request {

  //    Methods to be implemented in Client HTTP requests
  //          1. ValidateURL (i'm trying to keep this on the frontend side)
  //          2. SetHeaders (Map<String, String> headers) age, expiry, content-type etc
  //          3. Method (HTTPMethod method) GET, POST, PUT, DELETE. By Default GET

  // TODO
  //    Check how to use java.net.http.HttpClient for making HTTP requests and body handling
  //    for all HTTP methods and body types (JSON, XML, YAML, Form Data etc)

  /** */
  <T> CompletableFuture<T> asyncObject(Class<T> type, int code);

  /** */
  <T> CompletableFuture<T> asyncObject(Class<T> type);
}
