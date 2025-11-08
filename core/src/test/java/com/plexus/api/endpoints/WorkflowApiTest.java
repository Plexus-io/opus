package com.plexus.api.endpoints;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
class WorkflowApiTest {

  String yamlBody =
      "workflow:\n"
          + "  projectId: \"123e4567-e89b-12d3-a456-426614174000\"\n"
          + "  projectName: \"Sample Workflow\"\n"
          + "  projectNamespace: \"com.plexus.sample\"\n"
          + "  tasks:\n"
          + "    - id: \"223e4567-e89b-12d3-a456-426614174001\"\n"
          + "      taskName: \"Task One\"\n"
          + "      taskType: \"TypeA\"\n"
          + "      taskEvent: \"EventA\"\n"
          + "      taskMethod: \"MethodA\"\n"
          + "      onFailure: \"Retry\"\n"
          + "      retry: \"3\"\n"
          + "    - id: \"323e4567-e89b-12d3-a456-426614174002\"\n"
          + "      taskName: \"Task Two\"\n"
          + "      taskType: \"TypeB\"\n"
          + "      taskEvent: \"EventB\"\n"
          + "      taskMethod: \"MethodB\"\n"
          + "      onFailure: \"Abort\"\n"
          + "      retry: \"0\"\n";

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

    assert response.contains("Workflow 'Sample Workflow' processed successfully!");
  }
}
