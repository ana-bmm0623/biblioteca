package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class EmprestimoResourceTest {

        private Long usuarioId;
        private Long livroId;
        private Long emprestimoId;

        @BeforeEach
        public void setup() {
                // Limpar a base de dados (se existir o endpoint)
                // given().when().delete("/admin/clearDatabase").then().statusCode(204);

                // Criando usuário e livro para os testes
                given()
                                .contentType(ContentType.JSON)
                                .body("{\"nome\":\"Usuario Teste\", \"email\": \"usuario@email.com\", \"senha\": \"123456\", \"tipoUsuario\": \"CLIENTE\", \"status\": \"ATIVO\", \"endereco\": \"Rua A\", \"telefone\": \"123456789\"}")
                                .when().post("/usuarios")
                                .then()
                                .statusCode(201);

                given()
                                .contentType(ContentType.JSON)
                                .body("{\"titulo\":\"Livro Teste\", \"isbn\": \"1234567890\", \"anoPublicacao\": 2023, \"quantidadeDisponivel\": 5, \"valor\": 50.0, \"editoraId\": 1}")
                                .when().post("/livros")
                                .then()
                                .statusCode(201);

                // Extrai os IDs após confirmar que a criação foi bem-sucedida
                usuarioId = given().when().get("/usuarios").then().extract().path("[0].id");
                livroId = given().when().get("/livros").then().extract().path("[0].id");
        }

        @Test
        public void testInsert() {
                String json = String.format(
                                "{\"usuarioId\":%d, \"livroId\":%d, \"dataEmprestimo\":\"2023-12-01\", \"dataDevolucao\":\"2024-01-01\"}",
                                usuarioId, livroId);

                emprestimoId = given()
                                .contentType(ContentType.JSON)
                                .body(json)
                                .when().post("/emprestimos")
                                .then()
                                .statusCode(201)
                                .body("id", notNullValue())
                                .body("usuarioId", is(usuarioId.intValue()))
                                .body("livroId", is(livroId.intValue()))
                                .extract().path("id");
        }

        @Test
        public void testUpdate() {
                testInsert();

                String updateJson = String.format(
                                "{\"usuarioId\":%d, \"livroId\":%d, \"dataEmprestimo\":\"2023-12-01\", \"dataDevolucao\":\"2024-02-01\"}",
                                usuarioId, livroId);

                given()
                                .contentType(ContentType.JSON)
                                .body(updateJson)
                                .when().put("/emprestimos/" + emprestimoId)
                                .then()
                                .statusCode(200)
                                .body("dataDevolucao", is("2024-02-01"));
        }

        @Test
        public void testDelete() {
                testInsert();

                given()
                                .when().delete("/emprestimos/" + emprestimoId)
                                .then()
                                .statusCode(204);
        }

        @Test
        public void testFindById() {
                testInsert();

                given()
                                .when().get("/emprestimos/" + emprestimoId)
                                .then()
                                .statusCode(200)
                                .body("id", is(emprestimoId.intValue()));
        }

        @Test
        public void testFindByUsuarioId() {
                testInsert();

                given()
                                .when().get("/emprestimos/usuario/" + usuarioId)
                                .then()
                                .statusCode(200)
                                .body("[0].id", is(emprestimoId.intValue()));
        }

        @Test
        public void testFindAll() {
                testInsert();

                given()
                                .when().get("/emprestimos")
                                .then()
                                .statusCode(200)
                                .body("size()", is(1));
        }
}
