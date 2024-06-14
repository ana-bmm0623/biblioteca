package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class CategoriaResourceTest {

        @Test
        public void testInsert() {
                String json = "{\"nome\":\"Categoria Teste\"}";

                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/categorias")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Categoria Teste"));
        }

        @Test
        public void testUpdate() {
                String json = "{\"nome\":\"Categoria Teste\"}";

                // First insert a category
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/categorias")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Categoria Teste"));

                // Now update the category
                String updateJson = "{\"nome\":\"Categoria Teste Atualizada\"}";

                given()
                                .contentType(ContentType.JSON)
                                .body(updateJson)
                                .when().put("/categorias/1")
                                .then()
                                .statusCode(200)
                                .body("nome", is("Categoria Teste Atualizada"));
        }

        @Test
        public void testDelete() {
                String json = "{\"nome\":\"Categoria Teste\"}";

                // First insert a category
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/categorias")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Categoria Teste"));

                // Now delete the category
                given()
                                .when().delete("/categorias/1")
                                .then()
                                .statusCode(500);
        }

        @Test
        public void testFindById() {
                String json = "{\"nome\":\"Ficção\"}";

                // First insert a category
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/categorias")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Ficção"));

                // Now find the category by ID
                given()
                                .when().get("/categorias/1")
                                .then()
                                .statusCode(200)
                                .body("nome", is("Ficção"));
        }

        @Test
        public void testFindByNome() {
                String json = "{\"nome\":\"Categoria Teste\"}";

                // First insert a category
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/categorias")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Categoria Teste"));

                // Now find the category by name
                given()
                                .when().get("/categorias/nome/Categoria Teste")
                                .then()
                                .statusCode(200)
                                .body("[0].nome", is("Categoria Teste"));
        }

        @Test
        public void testFindAll() {
                String json = "{\"nome\":\"Ficção\"}";

                // First insert a category
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/categorias")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Ficção"));

                // Now find all categories
                given()
                                .when().get("/categorias")
                                .then()
                                .statusCode(200)
                                .body("[0].nome", is("Ficção"));
        }
}
