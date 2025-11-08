package com.plexus.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task {

  private static final Logger log = LogManager.getLogger();

  @JsonProperty("id")
  private UUID taskId;

  private String taskName;
  private String taskType;
  private String taskEvent;
  private String taskMethod;

  //    Requires another class to set the below as lists or of separate type Failure and Retry

  private String onFailure;
  private String retry;

  public Task() {}

  public UUID getTaskId() {
    return taskId;
  }

  public String getTaskName() {
    return taskName;
  }

  public String getTaskType() {
    return taskType;
  }

  public String getTaskEvent() {
    return taskEvent;
  }

  public String getTaskMethod() {
    return taskMethod;
  }

  public String getOnFailure() {
    return onFailure;
  }

  public void setTaskId(UUID taskId) {
    this.taskId = taskId;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public void setTaskType(String taskType) {
    this.taskType = taskType;
  }

  public void setTaskEvent(String taskEvent) {
    this.taskEvent = taskEvent;
  }

  public void setTaskMethod(String taskMethod) {
    this.taskMethod = taskMethod;
  }

  public void setOnFailure(String onFailure) {
    this.onFailure = onFailure;
  }

  public void setRetry(String retry) {
    this.retry = retry;
  }

  public String getRetry() {
    return retry;
  }
}
