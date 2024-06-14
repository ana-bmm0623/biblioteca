package br.unitins.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.dto.LivroDTO;
import br.unitins.dto.LivroResponseDTO;
import br.unitins.service.LivroService;
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

@Path("/livros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivroResource {

    @Inject
    LivroService service;

    private static final Logger LOG = Logger.getLogger(LivroResource.class);

    @POST
    @Transactional
    public Response insert(@Valid LivroDTO dto) {
        LOG.info("Inserindo Livro");
        LivroResponseDTO retorno = service.insert(dto);
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@Valid LivroDTO dto, @PathParam("id") Long id) {
        LOG.info("Atualizando Livro");
        LivroResponseDTO retorno = service.update(dto, id);
        return Response.ok(retorno).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando Livro");
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Transactional
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando Livro por ID");
        LivroResponseDTO retorno = service.findById(id);
        return Response.ok(retorno).build();
    }

    @GET
    @Transactional
    @Path("/titulo/{titulo}")
    public Response findByTitulo(@PathParam("titulo") String titulo) {
        LOG.info("Buscando Livros por Título");
        List<LivroResponseDTO> retorno = service.findByTitulo(titulo);
        return Response.ok(retorno).build();
    }

    @GET
    @Transactional
    public Response findAll() {
        LOG.info("Listando todos os Livros");
        List<LivroResponseDTO> retorno = service.findAll();
        return Response.ok(retorno).build();
    }
}
