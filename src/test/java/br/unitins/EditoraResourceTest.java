package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class EditoraResourceTest {

        @Test
        public void testInsert() {
                String json = "{\"nome\":\"Editora Teste\", \"cnpj\": \"12345678901234\", \"endereco\": \"Rua A\", \"telefone\": \"123456789\", \"email\": \"editora@email.com\"}"; // JSON
                                                                                                                                                                                    // completo
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/editoras")
                                .then()
                                .statusCode(201) // Status code correto para inserção bem-sucedida
                                .body("nome", is("Editora Teste"));
        }

        @Test
        public void testUpdate() {
                String json = "{\"nome\":\"Editora Teste\", \"cnpj\": \"12345678901234\", \"endereco\": \"Rua A\", \"telefone\": \"123456789\", \"email\": \"editora@email.com\"}";

                String updateJson = "{\"nome\":\"Editora Teste Atualizada\", \"cnpj\": \"12345678901234\", \"endereco\": \"Rua B\", \"telefone\": \"987654321\", \"email\": \"editora@email.com\"}"; // JSON
                                                                                                                                                                                                     // completo
                                                                                                                                                                                                     // com
                                                                                                                                                                                                     // nome
                                                                                                                                                                                                     // atualizado
                given()
                                .contentType(ContentType.JSON)
                                .body(updateJson)
                                .when().put("/editoras/1") // Assumin0g the ID is 1
                                .then()
                                .statusCode(200)
                                .body("nome", is("Editora Teste Atualizada")); // Verificação correta do nome atualizado
        }

        @Test
        public void testDelete() {
                String json = "{\"id\": 1, \"nome\": \"Editora Teste\", \"cnpj\": \"12345678901234\", \"endereco\": \"Rua A\", \"telefone\": \"123456789\", \"email\": \"editora@email.com\"}";

                // First insert an editora
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/editoras")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Editora Teste"));

                // Now delete the editora
                given()
                                .when().delete("/editoras/1")
                                .then()
                                .statusCode(500);
        }

        @Test
        public void testFindById() {
                String json = "{\"id\": 1, \"nome\": \"Rocco\", \"cnpj\": \"12345678901234\", \"endereco\": \"Rua A\", \"telefone\": \"123456789\", \"email\": \"editora@email.com\"}";

                // First insert an editora
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/editoras")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Rocco"));

                // Now find the editora by ID
                given()
                                .when().get("/editoras/1")
                                .then()
                                .statusCode(200)
                                .body("nome", is("Rocco"));
        }

        @Test
        public void testFindByNome() {
                String json = "{\"id\": 1, \"nome\": \"Editora Teste\", \"cnpj\": \"12345678901234\", \"endereco\": \"Rua A\", \"telefone\": \"123456789\", \"email\": \"editora@email.com\"}";

                // First insert an editora
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/editoras")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Editora Teste"));

                // Now find the editora by name
                given()
                                .when().get("/editoras/nome/Editora Teste")
                                .then()
                                .statusCode(200)
                                .body("[0].nome", is("Editora Teste"));
        }

        @Test
        public void testFindAll() {
                String json = "{\"id\": 1, \"nome\": \"Rocco\", \"cnpj\": \"12345678901234\", \"endereco\": \"Rua A\", \"telefone\": \"123456789\", \"email\": \"editora@email.com\"}";
                // First insert an editora
                given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/editoras")
                                .then()
                                .statusCode(201)
                                .body("nome", is("Rocco"));

                // Now find all editoras
                given()
                                .when().get("/editoras")
                                .then()
                                .statusCode(200)
                                .body("[0].nome", is("Rocco"));
        }
}
