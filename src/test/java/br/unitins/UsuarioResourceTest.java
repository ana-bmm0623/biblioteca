package br.unitins;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.dto.UsuarioDTO;
import br.unitins.dto.UsuarioResponseDTO;
import br.unitins.model.StatusUsuario;
import br.unitins.model.TipoUsuario;
import br.unitins.resource.UsuarioResource;
import br.unitins.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
public class UsuarioResourceTest {

    @Inject
    UsuarioResource resource;

    @Inject
    UsuarioService service;

    @Test
    @Transactional
    public void testInsertComDadosInvalidos() {
        // Nome em branco
        UsuarioDTO dtoInvalido = criarUsuarioDTO("null", "email@valido.com", "senha123", TipoUsuario.ALUNO,
                StatusUsuario.ATIVO, "Rua A", "123456789");
        Response response = resource.insert(dtoInvalido);
        assertEquals(201, response.getStatus());
    }

    @Test
    public void testFindByEmailInexistente() {
        assertThrows(NotFoundException.class, () -> resource.findByEmail("email-inexistente@email.com"));
    }

    @Test
    public void testFindAll() {
        UsuarioDTO usuario1DTO = criarUsuarioDTO("Maria", "maria@email.com", "senha123", TipoUsuario.PROFESSOR,
                StatusUsuario.ATIVO, "Rua B", "987654321");
        UsuarioDTO usuario2DTO = criarUsuarioDTO("João", "joao@email.com", "senha123", TipoUsuario.PROFESSOR,
                StatusUsuario.ATIVO, "Rua C", "555555555");

        service.insert(usuario1DTO);
        service.insert(usuario2DTO);

        Response response = resource.findAll();
        assertEquals(Status.OK.getStatusCode(), response.getStatus());

        @SuppressWarnings("unchecked")
        List<UsuarioResponseDTO> usuariosEncontrados = (List<UsuarioResponseDTO>) response.getEntity();
        assertEquals(4, usuariosEncontrados.size());
    }

    // Métodos auxiliares para criar DTOs
    private UsuarioDTO criarUsuarioDTO(String nome, String email, String senha, TipoUsuario tipoUsuario,
            StatusUsuario status, String endereco, String telefone) {
        return new UsuarioDTO(null, nome, email, senha, tipoUsuario, status, endereco, telefone);
    }
}
