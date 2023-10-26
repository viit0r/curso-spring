package com.viit0r.cursospring.integrationtest.controller.json;

import com.viit0r.cursospring.config.TestConfig;
import com.viit0r.cursospring.integrationtest.dto.PersonDTO;
import com.viit0r.cursospring.integrationtest.testcontainers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static PersonDTO personDTO;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        personDTO = new PersonDTO();
    }



    @Test
    @Order(1)
    public void testCreate() throws IOException {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_ORIGIN, "http://localhost:8080")
                .setBasePath("api/person/v1")
                .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given().spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .body(personDTO)
                    .when()
                        .post()
                    .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asString();

        PersonDTO pessoaCriada = objectMapper.readValue(content, PersonDTO.class);

        personDTO  = pessoaCriada;

        assertNotNull(pessoaCriada);
        assertNotNull(pessoaCriada.getId());
        assertNotNull(pessoaCriada.getPrimeiroNome());
        assertNotNull(pessoaCriada.getUltimoNome());
        assertNotNull(pessoaCriada.getEndereco());
        assertNotNull(pessoaCriada.getGenero());

        assertTrue(pessoaCriada.getId() > 0);

        assertEquals("Vitor", pessoaCriada.getPrimeiroNome());
        assertEquals("Porto", pessoaCriada.getUltimoNome());
        assertEquals("São Paulo - SP", pessoaCriada.getEndereco());
        assertEquals("Male", pessoaCriada.getGenero());
    }

    @Test
    @Order(2)
    public void testCreatewithWrongOrigin() throws IOException {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_ORIGIN, "https://www.viit0r.com.br")
                .setBasePath("api/person/v1")
                .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given().spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .body(personDTO)
                .when()
                .post()
                .then()
                .statusCode(403)
                .extract()
                .body()
                .asString();

        assertNotNull(content);

        assertEquals("Invalid CORS request", content);
    }

    @Test
    @Order(3)
    public void testFindById() throws IOException {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_ORIGIN, "http://localhost:8080")
                .setBasePath("api/person/v1")
                .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given().spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .pathParam("id", personDTO.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        PersonDTO pessoaRecuperada = objectMapper.readValue(content, PersonDTO.class);

        personDTO  = pessoaRecuperada;

        assertNotNull(pessoaRecuperada);
        assertNotNull(pessoaRecuperada.getId());
        assertNotNull(pessoaRecuperada.getPrimeiroNome());
        assertNotNull(pessoaRecuperada.getUltimoNome());
        assertNotNull(pessoaRecuperada.getEndereco());
        assertNotNull(pessoaRecuperada.getGenero());

        assertTrue(pessoaRecuperada.getId() > 0);

        assertEquals("Vitor", pessoaRecuperada.getPrimeiroNome());
        assertEquals("Porto", pessoaRecuperada.getUltimoNome());
        assertEquals("São Paulo - SP", pessoaRecuperada.getEndereco());
        assertEquals("Male", pessoaRecuperada.getGenero());
    }

    @Test
    @Order(4)
    public void testFindByIdWithWrongOrigin() throws IOException {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_ORIGIN, "https://www.viit0r.com.br")
                .setBasePath("api/person/v1")
                .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given().spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .pathParam("id", personDTO.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(403)
                .extract()
                .body()
                .asString();

        assertNotNull(content);

        assertEquals("Invalid CORS request", content);
    }

    private void mockPerson() {
        personDTO.setPrimeiroNome("Vitor");
        personDTO.setUltimoNome("Porto");
        personDTO.setEndereco("São Paulo - SP");
        personDTO.setGenero("Male");
    }
}
