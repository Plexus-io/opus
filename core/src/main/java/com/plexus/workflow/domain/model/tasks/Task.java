package com.plexus.workflow.domain.model.tasks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

  @Column(name = "task_config")
  @JdbcTypeCode(SqlTypes.JSON)
  private TaskConfig taskConfig;

  @Column(name = "task_status")
  private String taskStatus;

  @Column(name = "retry_config")
  @JdbcTypeCode(SqlTypes.JSON)
  private RetryConfig retryConfig;

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

  public String getTaskStatus() {
    return taskStatus;
  }

  public void setTaskId(UUID taskId) {
    this.taskId = taskId;
  }

  public TaskConfig getTaskConfig() {
    return taskConfig;
  }

  public RetryConfig getRetryConfig() {
    return retryConfig;
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

  public void setTaskStatus(String taskStatus) {
    this.taskStatus = taskStatus;
  }

  public void setTaskConfig(TaskConfig taskConfig) {
    this.taskConfig = taskConfig;
  }

  public void setRetryConfig(RetryConfig retryConfig) {
    this.retryConfig = retryConfig;
  }
}
