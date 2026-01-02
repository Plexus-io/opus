package com.plexus.workflow.domain.features.http;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import java.net.http.HttpClient;
import java.time.Duration;

public class HttpClientConfig {

  @Produces
  @ApplicationScoped
  public HttpClient httpClient() {
    return HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();
  }
}
