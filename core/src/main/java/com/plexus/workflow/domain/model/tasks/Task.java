package com.plexus.workflow.domain.model.tasks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "task_wf")
public class Task {

  @Id
  @Column(name = "task_id")
  private UUID taskId;

  @Column(name = "workflow_id")
  private UUID workflowId;

  @Column(name = "task_name")
  private String taskName;

  @Column(name = "task_type")
  private String taskType;

  //  TODO
  //  @Column(name = "task_config")
  //  private String taskConfig;

  @Column(name = "task_status")
  private String taskStatus;

  //  TODO
  //  @Column(name = "retry_config")
  //  private Json retryConfig;

  public UUID getTaskId() {
    return taskId;
  }

  public UUID getWorkflowId() {
    return workflowId;
  }

  public String getTaskName() {
    return taskName;
  }

  public String getTaskType() {
    return taskType;
  }

  //  public String getTaskConfig() {
  //    return taskConfig;
  //  }

  public String getTaskStatus() {
    return taskStatus;
  }

  //  public String getRetryConfig() {
  //    return retryConfig;
  //  }

  public void setTaskId(UUID taskId) {
    this.taskId = taskId;
  }

  public void setWorkflowId(UUID workflowId) {
    this.workflowId = workflowId;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public void setTaskType(String taskType) {
    this.taskType = taskType;
  }

  //  public void setTaskConfig(String taskConfig) {
  //    this.taskConfig = taskConfig;
  //  }

  public void setTaskStatus(String taskStatus) {
    this.taskStatus = taskStatus;
  }

  //  public void setRetryConfig(String retryConfig) {
  //    this.retryConfig = retryConfig;
  //  }
}
