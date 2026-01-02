package com.plexus.workflow.domain.features.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RedisPublisher {

  @Inject RedisDataSource redisDataSource;

  public void publishMessage(String message) {
    redisDataSource.pubsub(String.class).publish("my-channel", message);
  }
}
