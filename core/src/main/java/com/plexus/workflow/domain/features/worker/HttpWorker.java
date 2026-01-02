package com.plexus.workflow.domain.features.worker;

import com.plexus.workflow.domain.features.http.Client;
import jakarta.enterprise.context.ApplicationScoped;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ApplicationScoped
public class HttpWorker {

  private static final Logger log = LogManager.getLogger(HttpWorker.class);
  private final Client httpClient;

  public HttpWorker(Client httpClient) {
    this.httpClient = httpClient;
  }

  public CompletableFuture<String> performGetRequest(
      HttpResponse.BodyHandler<String> handler, String uri) {

    //        Caller to handle errors and response processing
    //        For now let this be under exceptionally block here, later we can move it to handle
    // method
    //        and create specific exceptions
    return httpClient
        .genericGetAsyncObject(handler, uri)
        .thenApply(HttpResponse::body)
        .exceptionally(
            e -> {
              log.error("Error performing GET request to {}: {}", uri, e.getMessage());
              return null;
            });
  }
}
