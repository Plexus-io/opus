-- This migration script creates the initial database schema for the workflow engine

-- Enable UUID generation function
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 1. Users Table
-- Stores user account information
CREATE TABLE users (
                       user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       email TEXT UNIQUE NOT NULL,
                       last_activity TIMESTAMPTZ,
                       created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                       img_url TEXT
);

-- 2. Project Table
-- Stores projects, which are owned by users
CREATE TABLE project (
                         project_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         user_id UUID NOT NULL,
                         namespace TEXT NOT NULL,

    -- A project belongs to one user
                         CONSTRAINT fk_project_user
                             FOREIGN KEY(user_id)
                                 REFERENCES users(user_id)
                                 ON DELETE CASCADE -- If a user is deleted, their projects are deleted
);

-- 3. Workflow Table
-- Stores workflow definitions, which belong to a project
CREATE TABLE workflow (
                          workflow_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          project_id UUID NOT NULL,
                          workflow_name TEXT NOT NULL,
                          isParent BOOLEAN DEFAULT FALSE,
                          parent_id UUID,
                          isChild BOOLEAN DEFAULT FALSE,
                          child_id UUID,
                          overallStatus VARCHAR(50), -- e.g., 'SUCCESS', 'FAIL', 'RETRY'

    -- A workflow belongs to one project
                          CONSTRAINT fk_workflow_project
                              FOREIGN KEY(project_id)
                                  REFERENCES project(project_id)
                                  ON DELETE CASCADE -- If a project is deleted, its workflows are deleted
);

-- 4. Task Workflow Table (task_wf)
-- Stores individual tasks that make up a workflow
CREATE TABLE task_wf (
                         task_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         workflow_id UUID NOT NULL,
                         task_name TEXT NOT NULL,
                         task_type VARCHAR(100),
                         task_config JSONB,
                         task_status VARCHAR(50), -- e.g., 'SUCCESS', 'FAIL', 'RETRY'
                         retry_config JSONB,

    -- A task belongs to one workflow
                         CONSTRAINT fk_task_workflow
                             FOREIGN KEY(workflow_id)
                                 REFERENCES workflow(workflow_id)
                                 ON DELETE CASCADE -- If a workflow is deleted, its tasks are deleted
);

-- 5. Task Versioning Table
-- Stores different versions of a specific task
CREATE TABLE task_versioning (
                                 version_id SERIAL PRIMARY KEY, -- 'int pk' auto-incrementing
                                 task_id UUID NOT NULL,
                                 version_name VARCHAR(100),
                                 created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                                 last_activity TIMESTAMPTZ,

    -- A version belongs to one task
                                 CONSTRAINT fk_version_task
                                     FOREIGN KEY(task_id)
                                         REFERENCES task_wf(task_id)
                                         ON DELETE CASCADE -- If a task is deleted, its versions are deleted
);

-- 6. Metrics/Logs Table
-- Stores logs and metrics for each task execution

CREATE TYPE log_level AS ENUM (
    'ERROR',
    'WARN',
    'INFO',
    'DEBUG',
    'TRACE'
    );

CREATE TABLE metrics (
                         id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         task_id UUID NOT NULL,
                         attempt_number INT DEFAULT 1,
                         status log_level, -- Using the ENUM type
                         "timestamp" TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP, -- Quoted, as 'timestamp' is a type name
                         logMessage TEXT,

    -- A metric log belongs to one task
                         CONSTRAINT fk_metrics_task
                             FOREIGN KEY(task_id)
                                 REFERENCES task_wf(task_id)
                                 ON DELETE CASCADE -- If a task is deleted, its logs are deleted
);
