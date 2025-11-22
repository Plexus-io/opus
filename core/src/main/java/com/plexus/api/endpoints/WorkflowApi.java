package com.plexus.api.endpoints;

import com.plexus.api.domain.WorkflowService;
import com.plexus.parser.Parser;
import com.plexus.workflow.domain.model.tasks.Task;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/api/workflow")
public class WorkflowApi {

  private final Application application;
  private static final Logger log = LogManager.getLogger(WorkflowApi.class);

  @Inject
  public WorkflowApi(Application application) {
    this.application = application;
  }

  @Inject WorkflowService workflowService;

  @POST
  @Path("/create")
  @Transactional
  // 👇 FIX: Add the standard YAML types
  @Consumes({"application/yaml", "text/yaml", "application/x-yaml"})
  @Produces(MediaType.TEXT_PLAIN)
  public String createWorkflow(Parser parser) {
    log.info("Creating workflow");

    String task =
        parser.workflow.getTasks().stream().map(Task::getTaskName).collect(Collectors.joining(","));
    workflowService.saveWorkflow(parser);
    return "Workflow '" + task + "' processed successfully!";
  }
}
