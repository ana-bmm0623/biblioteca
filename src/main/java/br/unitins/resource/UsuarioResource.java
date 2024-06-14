package br.unitins.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.dto.UsuarioDTO;
import br.unitins.dto.UsuarioResponseDTO;
import br.unitins.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    private static final Logger LOG = Logger.getLogger(UsuarioResource.class);

    @POST
    @Transactional
    public Response insert(@Valid UsuarioDTO dto) {
        LOG.info("Inserindo Usuário");
        UsuarioResponseDTO retorno = service.insert(dto);
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@Valid UsuarioDTO dto, @PathParam("id") Long id) {
        LOG.info("Atualizando Usuário");
        UsuarioResponseDTO retorno = service.update(dto, id);
        return Response.ok(retorno).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando Usuário");
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Transactional
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando Usuário por ID");
        UsuarioResponseDTO retorno = service.findById(id);
        return Response.ok(retorno).build();
    }

    @GET
    @Path("/email/{email}")
    public Response findByEmail(@PathParam("email") String email) {
        LOG.info("Buscando Usuário por Email");
        UsuarioResponseDTO retorno = service.findByEmail(email);
        return Response.ok(retorno).build();
    }

    @GET
    public Response findAll() {
        LOG.info("Listando todos os Usuários");
        List<UsuarioResponseDTO> retorno = service.findAll();
        return Response.ok(retorno).build();
    }
}
