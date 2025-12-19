package com.plexus.api.endpoints;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

@QuarkusTest
class WorkflowApiTest {

  private static final Logger log = LogManager.getLogger(WorkflowApiTest.class);

  String jsonBody =
      """
          {
            "workflow": {
              "projectId": "b0eebc99-9c0b-4ef8-bb6d-6bb9bd380b11",
              "projectName": "Sample Workflow",
              "projectNamespace": "sample_workflow",
              "tasks": [
                {
                  "taskId": "223e4567-e89b-12d3-a456-426614174001",
                  "workflowId": "c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c14",
                  "taskName": "Task One",
                  "taskType": "TypeA"
                },
                {
                  "taskId": "323e4567-e89b-12d3-a456-426614174002",
                  "workflowId": "c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c14",
                  "taskName": "Task Two",
                  "taskType": "TypeB"
                }
              ]
            }
          }
          """;

  String jsonBody1 =
      """
          {
            "workflow": {
              "projectId": "b0eebc99-9c0b-4ef8-bb6d-6bb9bd380b11",
              "projectName": "Sample Workflow",
              "projectNamespace": "sample_workflow",
              "tasks": [
                {
                  "taskId": "223e4567-e89b-12d3-a456-426614174003",
                  "workflowId": "c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c14",
                  "taskName": "Task One",
                  "taskType": "TypeA"
                },
                {
                  "taskId": "323e4567-e89b-12d3-a456-426614174004",
                  "workflowId": "c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c14",
                  "taskName": "Task Two",
                  "taskType": "TypeB"
                }
              ]
            }
          }
          """;

  @Test
  void testCreateWorkflowStatusCode() {
    given()
        .header("Content-Type", "application/json")
        .body(jsonBody)
        .when()
        .post("/api/workflow/create")
        .then()
        .statusCode(200)
        .extract()
        .response();
  }

  @Test
  void testResponseBody() {
    String response =
        given()
            .header("Content-Type", "application/json")
            .body(jsonBody1)
            .when()
            .post("/api/workflow/create")
            .then()
            .statusCode(200)
            .extract()
            .asString();

    log.error(response);
    assert response.contains("Workflow 'Task One,Task Two' processed successfully!");
  }
}
