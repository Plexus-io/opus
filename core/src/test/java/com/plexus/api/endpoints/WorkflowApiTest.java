package com.plexus.api.endpoints;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

@QuarkusTest
class WorkflowApiTest {

  private static final Logger log = LogManager.getLogger(WorkflowApiTest.class);

  private final UUID transactionId = UUID.fromString("624deea6-b709-470c-8c39-4b5511281492");
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
                  "taskName": "Task Three - Fetch JSON Data",
                  "taskType": "TypeC",
                  "taskConfig": {
                    "request": {
                      "method": "GET",
                      "url": "https://test.url.com/get-data",
                      "headers": {
                        "Accept": "application/json"
                      },
                      "queryParams": {
                        "limit": "10",
                        "offset": "0"
                      }
                    }
                  },
                  "retryConfig": {
                    "maxRetries": 3,
                    "retryDelayMs": 1000,
                    "backoffStrategy": "exponential",
                    "retryOnStatusCodes": [429, 500, 502, 503, 504],
                    "retryOnNetworkError": true,
                    "expectJson": true
                  }
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
                  "taskName": "Task Three - Fetch JSON Data",
                  "taskType": "TypeC",
                  "taskConfig": {
                    "request": {
                      "method": "GET",
                      "url": "https://test.url.com/get-data",
                      "headers": {
                        "Accept": "application/json"
                      },
                      "queryParams": {
                        "limit": "10",
                        "offset": "0"
                      }
                    }
                  },
                  "retryConfig": {
                    "maxRetries": 3,
                    "retryDelayMs": 1000,
                    "backoffStrategy": "exponential",
                    "retryOnStatusCodes": [429, 500, 502, 503, 504],
                    "retryOnNetworkError": true,
                    "expectJson": true
                  }
                }
              ]
            }
          }
          """;

  @Test
  void testCreateWorkflowStatusCode() {
    given()
        .header("Content-Type", "application/json")
        .header("X-Transaction-id", transactionId.toString())
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
    given()
        .header("Content-Type", "application/json")
        .header("X-Transaction-id", transactionId.toString())
        .body(jsonBody1)
        .when()
        .post("/api/workflow/create")
        .then()
        .statusCode(200)
        .body("data", Matchers.equalTo("Task Three - Fetch JSON Data"))
        .body("requestId", Matchers.equalTo(transactionId.toString()))
        .body("response", Matchers.equalTo("SUCCESS"))
        .toString();
  }
}
