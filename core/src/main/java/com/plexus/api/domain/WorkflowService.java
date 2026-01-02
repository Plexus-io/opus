package com.plexus.api.domain;

import com.plexus.api.endpoints.WorkflowApi;
import com.plexus.parser.Parser;
import com.plexus.workflow.domain.features.redis.RedisPublisher;
import com.plexus.workflow.domain.model.tasks.Task;
import com.plexus.workflow.domain.model.tasks.TaskConfig;
import com.plexus.workflow.domain.model.tasks.TaskRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

@ApplicationScoped
public class WorkflowService {

  @Inject TaskRepo taskRepo;
  @Inject
  RedisPublisher redisPublisher;

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

  public void executeWorkflow(String workflowId, String taskId) {
    // Implementation for executing the workflow goes here

    try {
        Task task = taskRepo.find("id=?1", UUID.fromString(taskId)).firstResult();
        redisPublisher.publishMessage(task.getTaskConfig().getRequest().getUrl());
        log.info(task.getTaskConfig().getRequest().getUrl());
    } catch (Exception e) {
      log.error(e);
    }
  }
}
