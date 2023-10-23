package com.viit0r.cursospring.integrationtest.swagger;

import static io.restassured.RestAssured.given;

import com.viit0r.cursospring.config.TestConfig;
import com.viit0r.cursospring.integrationtest.testcontainers.AbstractIntegrationTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void shouldDisplaySwaggerUIPage() {
        var content = given()
                        .basePath("/swagger-ui/index.html")
                        .port(TestConfig.SERVER_PORT)
                        .when()
                            .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asString();

        assertTrue(content.contains("Swagger UI"));
    }
}
