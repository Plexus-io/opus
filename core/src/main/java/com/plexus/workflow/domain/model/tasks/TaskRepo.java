package com.plexus.workflow.domain.model.tasks;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class TaskRepo implements PanacheRepository<Task> {
}
