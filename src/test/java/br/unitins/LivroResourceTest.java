package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class LivroResourceTest {

    @BeforeEach
    public void setup() {
        // Limpa a base de dados antes de cada teste
        given().when().delete("/admin/clearDatabase").then().statusCode(204);
    }

    @Test
    public void testInsert() {
        String json = "{\"titulo\":\"Livro Teste\", \"autor\":\"Autor Teste\", \"editora\":\"Editora Teste\"}";

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when().post("/livros")
                .then()
                .statusCode(201)
                .body("titulo", is("Livro Teste"))
                .body("autor", is("Autor Teste"))
                .body("editora", is("Editora Teste"));
    }

    @Test
    public void testUpdate() {
        String json = "{\"titulo\":\"Livro Teste\", \"autor\":\"Autor Teste\", \"editora\":\"Editora Teste\"}";

        // First insert a livro
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when().post("/livros")
                .then()
                .statusCode(201)
                .body("titulo", is("Livro Teste"))
                .body("autor", is("Autor Teste"))
                .body("editora", is("Editora Teste"));

        // Now update the livro
        String updateJson = "{\"titulo\":\"Livro Teste Atualizado\", \"autor\":\"Autor Teste Atualizado\", \"editora\":\"Editora Teste Atualizada\"}";

        given()
                .contentType(ContentType.JSON)
                .body(updateJson)
                .when().put("/livros/1")
                .then()
                .statusCode(200)
                .body("titulo", is("Livro Teste Atualizado"))
                .body("autor", is("Autor Teste Atualizado"))
                .body("editora", is("Editora Teste Atualizada"));
    }

    @Test
    public void testDelete() {
        String json = "{\"titulo\":\"Livro Teste\", \"autor\":\"Autor Teste\", \"editora\":\"Editora Teste\"}";

        // First insert a livro
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when().post("/livros")
                .then()
                .statusCode(201)
                .body("titulo", is("Livro Teste"))
                .body("autor", is("Autor Teste"))
                .body("editora", is("Editora Teste"));

        // Now delete the livro
        given()
                .when().delete("/livros/1")
                .then()
                .statusCode(204);
    }

    @Test
    public void testFindById() {
        String json = "{\"titulo\":\"Livro Teste\", \"autor\":\"Autor Teste\", \"editora\":\"Editora Teste\"}";

        // First insert a livro
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when().post("/livros")
                .then()
                .statusCode(201)
                .body("titulo", is("Livro Teste"))
                .body("autor", is("Autor Teste"))
                .body("editora", is("Editora Teste"));

        // Now find the livro by ID
        given()
                .when().get("/livros/1")
                .then()
                .statusCode(200)
                .body("titulo", is("Livro Teste"))
                .body("autor", is("Autor Teste"))
                .body("editora", is("Editora Teste"));
    }

    @Test
    public void testFindByTitulo() {
        String json = "{\"titulo\":\"Livro Teste\", \"autor\":\"Autor Teste\", \"editora\":\"Editora Teste\"}";

        // First insert a livro
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when().post("/livros")
                .then()
                .statusCode(201)
                .body("titulo", is("Livro Teste"))
                .body("autor", is("Autor Teste"))
                .body("editora", is("Editora Teste"));

        // Now find the livro by title
        given()
                .when().get("/livros/titulo/Livro Teste")
                .then()
                .statusCode(200)
                .body("[0].titulo", is("Livro Teste"))
                .body("[0].autor", is("Autor Teste"))
                .body("[0].editora", is("Editora Teste"));
    }

    @Test
    public void testFindAll() {
        String json = "{\"titulo\":\"Livro Teste\", \"autor\":\"Autor Teste\", \"editora\":\"Editora Teste\"}";

        // First insert a livro
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when().post("/livros")
                .then()
                .statusCode(201)
                .body("titulo", is("Livro Teste"))
                .body("autor", is("Autor Teste"))
                .body("editora", is("Editora Teste"));

        // Now find all livros
        given()
                .when().get("/livros")
                .then()
                .statusCode(200)
                .body("[0].titulo", is("Livro Teste"))
                .body("[0].autor", is("Autor Teste"))
                .body("[0].editora", is("Editora Teste"));
    }
}
