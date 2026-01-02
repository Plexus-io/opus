package com.plexus.api.domain;

public class ExecutionDTO {
  public enum Mode {
    ALL,
    SINGLE
  }

  private String workflowId;
  private Mode mode;
  private String taskId;

  public ExecutionDTO(String workflowId, Mode mode, String taskId) {
    this.workflowId = workflowId;
    this.mode = mode;
    this.taskId = taskId;
  }

  public String getWorkflowId() {
    return workflowId;
  }

  public Mode getMode() {
    return mode;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId;
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }
}
