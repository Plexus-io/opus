package com.plexus.workflow.domain.features.redis.Channel;

import com.plexus.workflow.domain.model.tasks.TaskConfig;

public interface ChannelHandler {

  //    TODO
  //      This is a barebones interface for channel handlers.
  //      We can expand this to include methods for subscribing, unsubscribing, etc.
  //      even add default methods for common functionality.
  //      also need to differentiate between different message types, maybe use generics?
  String getChannelName();

  void handleMessage(TaskConfig message);
}
