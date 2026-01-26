package com.plexus.workflow.domain.features.redis;

import com.plexus.workflow.domain.model.tasks.TaskConfig;
import io.quarkus.redis.datasource.RedisDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class RedisPublisher {

  @Inject RedisDataSource redisDataSource;

  public void publishMessageForSingleTask(TaskConfig message) {
    redisDataSource.pubsub(TaskConfig.class).publish("http-channel", message);
  }

  public void publishMessageForEntireWorkflow(List<TaskConfig> message) {

    //    change variable from VAR to something else

    var pubsub = redisDataSource.pubsub(TaskConfig.class);

    //    pubsub var has the payload type of taskConfig class, this is then looped for every task
    // present in the workflow , since
    message.forEach(msg -> pubsub.publish("http-channel", msg));
  }

  public void publishResponseMessage(String message) {
    redisDataSource.pubsub(String.class).publish("response-channel", message);
  }
}
