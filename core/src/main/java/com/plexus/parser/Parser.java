package com.plexus.parser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plexus.workflow.Workflow;

public class Parser {

  public Parser() {
    // Default no-argument constructor required by Jackson
  }

  @JsonProperty("workflow")
  public Workflow workflow;
}
