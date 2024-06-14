package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class AutorResourceTest {

        @Test
        public void testInsert() {
                String json = "{\"nome\":\"Autor Teste\"}";

                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/autores")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Autor Teste"));
        }

        @Test
        public void testUpdate() {
                String json = "{\"nome\":\"Autor Teste\"}";

                // First insert an author
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/autores")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Autor Teste"));

                // Now update the author
                String updateJson = "{\"nome\":\"Autor Teste Atualizado\"}";

                given()
                                .contentType(ContentType.JSON)
                                .body(updateJson)
                                .when().put("/autores/1")
                                .then()
                                .statusCode(200)
                                .body("nome", is("Autor Teste Atualizado"));
        }

        @Test
        public void testDelete() {
                String json = "{\"nome\":\"Autor Teste\"}";

                // First insert an author
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/autores")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Autor Teste"));

                // Now delete the author
                given()
                                .when().delete("/autores/1")
                                .then()
                                .statusCode(500);
        }

        @Test
        public void testFindById() {
                String json = "{\"nome\":\"J.K. Rowling\"}";

                // First insert an author
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/autores")
                                .then()
                                .statusCode(201)
                                .body("nome", is("J.K. Rowling"));

                // Now find the author by ID
                given()
                                .when().get("/autores/1")
                                .then()
                                .statusCode(200)
                                .body("nome", is("J.K. Rowling"));
        }

        @Test
        public void testFindByNome() {
                String json = "{\"nome\":\"Autor Teste\"}";

                // First insert an author
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/autores")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Autor Teste"));

                // Now find the author by name
                given()
                                .when().get("/autores/nome/Autor Teste")
                                .then()
                                .statusCode(200)
                                .body("[0].nome", is("Autor Teste"));
        }

        @Test
        public void testFindAll() {
                String json = "{\"nome\":\"J.K. Rowling\"}";

                // First insert an author
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/autores")
                                .then()
                                .statusCode(201)
                                .body("nome", is("J.K. Rowling"));

                // Now find all authors
                given()
                                .when().get("/autores")
                                .then()
                                .statusCode(200)
                                .body("[0].nome", is("J.K. Rowling"));
        }
}
