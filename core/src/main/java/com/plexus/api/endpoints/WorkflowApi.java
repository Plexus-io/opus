package com.plexus.api.endpoints;

import com.plexus.api.domain.ExecutionDTO;
import com.plexus.api.domain.GenericResponse;
import com.plexus.api.domain.WorkflowService;
import com.plexus.parser.Parser;
import com.plexus.workflow.domain.model.tasks.Task;
import io.vertx.core.http.HttpServerResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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

  @Inject HttpServerResponse statusResponse;

  @POST
  @Path("/create")
  @Transactional
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createWorkflow(
      Parser parser, @HeaderParam("X-transaction-id") String transactionId)
      throws IllegalArgumentException {
    log.info("Creating workflow");

    String task =
        parser.workflow.getTasks().stream().map(Task::getTaskName).collect(Collectors.joining(","));

    String id = transactionId.toString();
    String message = null;
    if (statusResponse.getStatusCode() == 200) {
      message = "SUCCESS";
    } else {
      message = "FAILURE";
    }
    GenericResponse<Object> response = new GenericResponse<>(task, transactionId, message);
    workflowService.saveWorkflow(parser);
    return Response.ok(response).header("X-transaction-id", transactionId).build();
  }

  @POST
  @Path("/execute")
  @Transactional
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response executeWorkflow(@HeaderParam("X-transaction-id") String transactionId, ExecutionDTO executionDTO)
      throws IllegalArgumentException {

    //    Return json with status of execution
    //    executionStatus: IN_PROGRESS, COMPLETED, FAILED
      workflowService.executeWorkflow(executionDTO.getTaskId(), executionDTO.getWorkflowId());
    GenericResponse<Object> response = new GenericResponse<>(null, transactionId, "IN_PROGRESS");
    return Response.ok(response).header("X-transaction-id", transactionId).build();
  }
}
