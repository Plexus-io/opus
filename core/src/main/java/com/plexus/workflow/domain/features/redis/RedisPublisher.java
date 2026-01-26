package com.plexus.workflow.domain.features.redis;

import com.plexus.workflow.domain.model.tasks.TaskConfig;
import io.quarkus.redis.datasource.RedisDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RedisPublisher {

  @Inject RedisDataSource redisDataSource;

  public void publishMessage(TaskConfig message) {
    redisDataSource.pubsub(TaskConfig.class).publish("http-channel", message);
  }

  public void publishResponseMessage(String message) {
    redisDataSource.pubsub(String.class).publish("response-channel", message);
  }
}
