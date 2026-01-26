package com.plexus.workflow.domain.features.redis;

import com.plexus.workflow.domain.features.redis.Channel.ChannelHandler;
import com.plexus.workflow.domain.model.tasks.TaskConfig;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.pubsub.ReactivePubSubCommands;
import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ApplicationScoped
public class RedisSubscriber {

  //  Simple Redis subscriber that listens to messages on "my-channel" and prints them to the
  // console.
  @Inject ReactiveRedisDataSource redisDataSource;

  @Inject Instance<ChannelHandler> handlers;

  private static final Logger log = LogManager.getLogger(RedisSubscriber.class);

  void onStart(@Observes StartupEvent ev) {
    String[] channels =
        handlers.stream().map(ChannelHandler::getChannelName).toArray(String[]::new);
    ReactivePubSubCommands<TaskConfig> pubSub = redisDataSource.pubsub(TaskConfig.class);
    log.info("Subscribing to channels: {}", Arrays.toString(channels));
    pubSub
        .subscribeAsMessages(channels)
        .emitOn(Infrastructure.getDefaultWorkerPool())
        .subscribe()
        .with(
            msg -> dispatch(msg.getChannel(), msg.getPayload()),
            failure -> log.error(failure.getMessage(), failure));
  }

  public void dispatch(String channel, TaskConfig payload) {
    handlers.stream()
        .filter(handler -> handler.getChannelName().equals(channel))
        .findFirst()
        .ifPresent(
            handler -> {
              try {
                handler.handleMessage(payload);
                log.info("Payload of type {} sent", payload.getRequest().getMethod());
              } catch (Exception e) {
                log.error("Handler failed", e);
              }
            });
  }
}
