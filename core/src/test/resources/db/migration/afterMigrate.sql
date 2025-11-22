
-- Note: UUIDs are hardcoded here for demonstration.
-- In a real application, you might use uuid_generate_v4() directly or
-- capture the generated UUIDs from previous inserts (e.g., using RETURNING)
-- to populate foreign key fields.

-- === 1. Insert Users ===

-- Insert a basic user
INSERT INTO users (user_id, email, last_activity, img_url)
VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'alice@example.com', CURRENT_TIMESTAMP, 'https://example.com/images/alice.png');

-- Insert another user with minimal info (letting defaults work)
INSERT INTO users (user_id, email)
VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'bob@example.com');

-- Insert a third user
INSERT INTO users (user_id, email, last_activity, img_url)
VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'charlie@example.com', '2025-11-15T10:00:00Z', 'https://example.com/images/charlie.png');


-- === 2. Insert Projects ===

-- Insert a project for Alice
INSERT INTO project (project_id, user_id, namespace)
VALUES
    ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380b11', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'alice-personal-space');

-- Insert two projects for Charlie
INSERT INTO project (project_id, user_id, namespace)
VALUES
    ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380b12', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'charlie-data-pipelines');

INSERT INTO project (project_id, user_id, namespace)
VALUES
    ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380b13', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'charlie-web-analytics');


-- === 3. Insert Workflows ===

-- Insert a simple workflow for Alice's project
INSERT INTO workflow (workflow_id, project_id, workflow_name, overallStatus)
VALUES
    ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c11', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380b11', 'Daily Report Generation', 'SUCCESS');

-- Insert a parent workflow for Charlie's 'data-pipelines' project
INSERT INTO workflow (workflow_id, project_id, workflow_name, isParent, overallStatus)
VALUES
    ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c12', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380b12', 'Main ETL Parent', TRUE, 'RUNNING');

-- Insert a child workflow linked to the parent above
-- Note: 'parent_id' links to the 'workflow_id' of the parent.
INSERT INTO workflow (workflow_id, project_id, workflow_name, isChild, parent_id, overallStatus)
VALUES
    ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c13', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380b12', 'Extract From Source A', TRUE, 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c12', 'SUCCESS');

-- Insert another child workflow for the same parent
INSERT INTO workflow (workflow_id, project_id, workflow_name, isChild, parent_id, overallStatus)
VALUES
    ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c14', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380b12', 'Transform Data', TRUE, 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c12', 'RUNNING');

-- Insert a workflow that failed for Charlie's 'web-analytics' project
INSERT INTO workflow (workflow_id, project_id, workflow_name, overallStatus)
VALUES
    ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c15', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380b13', 'Hourly Dashboard Refresh', 'FAIL');