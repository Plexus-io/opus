package com.plexus.workflow.domain.model.metrics;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "metrics")
public class Metric {

  @Id private UUID id;
  private UUID task_id;
  private Integer attempt_number;
  private String status;
  private Date timestamp;
  private String logMessage;
}
