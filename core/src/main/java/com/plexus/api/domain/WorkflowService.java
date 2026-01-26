package com.plexus.api.domain;

import com.plexus.api.endpoints.WorkflowApi;
import com.plexus.parser.Parser;
import com.plexus.workflow.domain.features.redis.RedisPublisher;
import com.plexus.workflow.domain.model.tasks.Task;
import com.plexus.workflow.domain.model.tasks.TaskRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ApplicationScoped
public class WorkflowService {

  @Inject TaskRepo taskRepo;
  @Inject RedisPublisher redisPublisher;

  public WorkflowService() {}

  private static final Logger log = LogManager.getLogger(WorkflowApi.class);

  public Task saveWorkflow(Parser parser) {
    try {
      taskRepo.persist(parser.workflow.getTasks());
      return parser.workflow.getTasks().get(0);
    } catch (Exception e) {
      log.error(e);
    }
    return null;
  }

  public void executeWorkflow(String taskId, String workflowId) {
    // Decide which id to use (taskId has priority). Validate inputs and guard against nulls.
    try {
      if (taskId != null && !taskId.isEmpty()) {
        // Use taskId
        try {
          Task task = taskRepo.find("id=?1", UUID.fromString(taskId)).firstResult();
          if (task != null) {
            redisPublisher.publishMessageForSingleTask(task.getTaskConfig());
          } else {
            log.warn("No task found for id {}", taskId);
          }
        } catch (IllegalArgumentException iae) {
          log.error("Invalid taskId (not a UUID): {}", taskId, iae);
        }
      } else if (workflowId != null && !workflowId.isEmpty()) {
        // Use workflowId
        try {
          List<Task> wfTask = taskRepo.find("workflowId=?1", UUID.fromString(workflowId)).list();
          if (wfTask != null) {
            redisPublisher.publishMessageForEntireWorkflow(
                wfTask.stream().map(Task::getTaskConfig).collect(Collectors.toList()));
          } else {
            log.warn("No workflow task found for id {}", workflowId);
          }
        } catch (IllegalArgumentException iae) {
          log.error("Invalid workflowId (not a UUID): {}", workflowId, iae);
        }
      } else {
        log.warn("Neither taskId nor workflowId provided - nothing to execute");
      }
    } catch (Exception e) {
      log.error("Error executing workflow", e);
    }
  }
}
