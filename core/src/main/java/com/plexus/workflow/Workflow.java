package com.plexus.workflow;

import com.plexus.workflow.domain.model.tasks.Task;
import java.util.List;
import java.util.UUID;

public class Workflow {
  private UUID projectId;
  private String projectName;
  private String projectNamespace;

  // boilerplate for type Task
  protected List<Task> tasks;

  public Workflow() {
    // Default no-argument constructor required by Jackson
  }

  public UUID getProjectId() {
    return projectId;
  }

  public String getProjectName() {
    return projectName;
  }

  public String getProjectNamespace() {
    return projectNamespace;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setProjectId(UUID projectId) {
    this.projectId = projectId;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public void setProjectNamespace(String projectNamespace) {
    this.projectNamespace = projectNamespace;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }
}
