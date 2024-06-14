package br.unitins.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.dto.AutorDTO;
import br.unitins.dto.AutorResponseDTO;
import br.unitins.service.AutorService;
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

@Path("/autores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutorResource {

    @Inject
    AutorService service;

    private static final Logger LOG = Logger.getLogger(AutorResource.class);

    @POST
    @Transactional
    public Response insert(@Valid AutorDTO dto) {
        LOG.info("Inserindo Autor");
        AutorResponseDTO retorno = service.insert(dto);
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@Valid AutorDTO dto, @PathParam("id") Long id) {
        LOG.info("Atualizando Autor");
        AutorResponseDTO retorno = service.update(dto, id);
        return Response.ok(retorno).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando Autor");
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Transactional
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando Autor por ID");
        AutorResponseDTO retorno = service.findById(id);
        return Response.ok(retorno).build();
    }

    @GET
    @Transactional
    @Path("/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Buscando Autores por nome");
        List<AutorResponseDTO> retorno = service.findByNome(nome);
        return Response.ok(retorno).build();
    }

    @GET
    @Transactional
    public Response findAll() {
        LOG.info("Listando todos os Autores");
        List<AutorResponseDTO> retorno = service.findAll();
        return Response.ok(retorno).build();
    }
}