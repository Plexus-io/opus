package com.plexus.workflow.domain.model.metrics;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "task_versioning")
public class Versions {

  @Id
  @Column(name = "version_id")
  private Integer versionId;

  @Column(name = "task_id")
  private UUID taskId;

  @Column(name = "version_name")
  private String versionName;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "last_activity")
  private Date lastActivity;

  public Integer getVersionId() {
    return versionId;
  }

  public UUID getTaskId() {
    return taskId;
  }

  public String getVersionName() {
    return versionName;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getLastActivity() {
    return lastActivity;
  }

  public void setVersionId(Integer versionId) {
    this.versionId = versionId;
  }

  public void setTaskId(UUID taskId) {
    this.taskId = taskId;
  }

  public void setVersionName(String versionName) {
    this.versionName = versionName;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public void setLastActivity(Date lastActivity) {
    this.lastActivity = lastActivity;
  }
}
