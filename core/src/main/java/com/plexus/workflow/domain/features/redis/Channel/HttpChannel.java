package com.plexus.workflow.domain.features.redis.Channel;

import com.plexus.workflow.domain.features.redis.RedisPublisher;
import com.plexus.workflow.domain.features.worker.HttpWorker;
import com.plexus.workflow.domain.model.tasks.TaskConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.net.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ApplicationScoped
public class HttpChannel implements ChannelHandler {

  private static final Logger log = LogManager.getLogger(HttpChannel.class);
  @Inject HttpWorker httpWorker;

  @Inject RedisPublisher redisPublisher;

  @Override
  public String getChannelName() {
    return "http-channel";
  }

  @Override
  public void handleMessage(TaskConfig message) {

    String type = message.getRequest().getMethod();
    String url = message.getRequest().getUrl();
    log.info(type);

    if (type.equalsIgnoreCase("GET")) {
      httpWorker
          .performGetRequest(HttpResponse.BodyHandlers.ofString(), url)
          .thenAccept(
              responseBody -> {
                // Handle the response body as needed
                redisPublisher.publishResponseMessage(responseBody);
              })
          .exceptionally(
              e -> {
                // Handle exceptions as needed
                log.error("Unable to perform HTTP GET request: {}", e.getMessage());
                return null;
              });
    }

    if (type.equalsIgnoreCase("POST")) {
      httpWorker
          .performPostRequest(
              HttpResponse.BodyHandlers.ofString(),
              url,
              message.getRequest().getBody(),
              message.getRequest().getHeaders().get("Content-Type"))
          .thenAccept(
              responseBody -> {
                // Handle the response body for POST request
                redisPublisher.publishResponseMessage(responseBody);
              })
          .exceptionally(
              e -> {
                // Handle exceptions as needed
                log.error("Unable to perform HTTP POST request: {}", e.getMessage());
                return null;
              });
    }
  }
}
