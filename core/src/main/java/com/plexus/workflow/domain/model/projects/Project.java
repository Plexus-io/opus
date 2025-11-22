package com.plexus.workflow.domain.model.projects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "project")
public class Project {

  @Id
  @Column(name = "project_id")
  private UUID projectId;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "namespace")
  private String namespace;
}
