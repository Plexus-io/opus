package com.plexus.workflow.domain.features.redis;

import com.plexus.workflow.domain.features.worker.HttpWorker;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.pubsub.ReactivePubSubCommands;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import java.net.http.HttpResponse;

@ApplicationScoped
public class RedisSubscriber {

  //  Simple Redis subscriber that listens to messages on "my-channel" and prints them to the
  // console.
  @Inject ReactiveRedisDataSource redisDataSource;

  @Inject HttpWorker httpWorker;

  void onStart(@Observes StartupEvent ev) {
    ReactivePubSubCommands<String> pubSub = redisDataSource.pubsub(String.class);

    pubSub
        .subscribeAsMessages("my-channel")
        .subscribe()
        .with(
            msg -> {
              String message = msg.getPayload();
              String channel = msg.getChannel();
              switch (channel) {
                case "http" ->
                    httpWorker.performGetRequest(HttpResponse.BodyHandlers.ofString(), message);
              }
            });
  }
}
