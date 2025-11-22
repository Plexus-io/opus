package com.plexus.api.endpoints;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
class WorkflowApiTest {

  private static final Logger log = LogManager.getLogger(WorkflowApiTest.class);

//  TODO
//  format below yaml
  String yamlBody =
      """
              workflow:
                                         projectId: "b0eebc99-9c0b-4ef8-bb6d-6bb9bd380b11"
                                         projectName: "Sample Workflow"
                                         projectNamespace: "sample_workflow"
                                         tasks:
                                           - taskId: "223e4567-e89b-12d3-a456-426614174001"
                                             workflowId: "c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c14"
                                             taskName: "Task One"
                                             taskType: "TypeA"
                                           - taskId: "323e4567-e89b-12d3-a456-426614174002"
                                             workflowId: "c0eebc99-9c0b-4ef8-bb6d-6bb9bd380c14"
                                             taskName: "Task Two"
                                             taskType: "TypeB"
      """;

  @BeforeEach
  void setupRestAssured() {
    // Set this configuration as the default for all tests in this class
    RestAssured.config =
        RestAssuredConfig.config()
            .encoderConfig(
                encoderConfig()
                    .encodeContentTypeAs(
                        "application/x-yaml", io.restassured.http.ContentType.TEXT));
  }

  @Test
  void testCreateWorkflowStatusCode() {
    given()
        .config(
            RestAssuredConfig.config()
                .encoderConfig(
                    encoderConfig()
                        .encodeContentTypeAs(
                            "application/x-yaml", io.restassured.http.ContentType.TEXT)))
        .header("Content-Type", "application/x-yaml")
        .body(yamlBody)
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
            .header("Content-Type", "application/x-yaml")
            .body(yamlBody)
            .when()
            .post("/api/workflow/create")
            .then()
            .statusCode(200)
            .extract()
            .asString();

    log.error(response);
    assert response.contains("Workflow 'Task One,Task Two' processed successfully!");
  }

  @Test
  void testCreateWorkflow_Debug() {
    // 1. Perform the request but DO NOT assert status code yet
    Response response =
        given()
            .log()
            .uri() // Log the URL
            .log()
            .headers() // Log headers to check Content-Type
            .log()
            .body() // Log the YAML being sent
            .header("Content-Type", "application/x-yaml")
            .body(yamlBody)
            .when()
            .post("/api/workflow/create")
            .then()
            .extract()
            .response();

    // 2. Check the status code manually
    if (response.statusCode() != 200) {
      // 3. If it failed, PRINT the response body to see the error
      String responseBody = response.getBody().asString();

      System.err.println("========================================");
      System.err.println("TEST FAILED - SERVER RESPONSE:");
      System.err.println(responseBody);
      System.err.println("========================================");

      // 4. Fail the test with a descriptive message
      fail("Expected 200 OK but got " + response.statusCode() + ". Response body: " + responseBody);
    }

    // 5. If we get here, it passed
    assertEquals(200, response.statusCode());
  }
}
