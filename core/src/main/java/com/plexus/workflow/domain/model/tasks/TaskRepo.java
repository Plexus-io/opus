package com.plexus.workflow.domain.model.tasks;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskRepo implements PanacheRepository<Task> {}
