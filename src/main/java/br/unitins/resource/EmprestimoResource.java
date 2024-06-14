package br.unitins.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.dto.EmprestimoDTO;
import br.unitins.dto.EmprestimoResponseDTO;
import br.unitins.service.EmprestimoService;
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

@Path("/emprestimos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmprestimoResource {

    @Inject
    EmprestimoService service;

    private static final Logger LOG = Logger.getLogger(EmprestimoResource.class);

    @POST
    @Transactional
    public Response insert(@Valid EmprestimoDTO dto) {
        LOG.info("Inserindo Empréstimo");
        EmprestimoResponseDTO retorno = service.insert(dto);
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@Valid EmprestimoDTO dto, @PathParam("id") Long id) {
        LOG.info("Atualizando Empréstimo");
        EmprestimoResponseDTO retorno = service.update(dto, id);
        return Response.ok(retorno).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando Empréstimo");
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Transactional
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando Empréstimo por ID");
        EmprestimoResponseDTO retorno = service.findById(id);
        return Response.ok(retorno).build();
    }

    @GET
    @Transactional
    @Path("/usuario/{usuarioId}")
    public Response findByUsuarioId(@PathParam("usuarioId") Long usuarioId) {
        LOG.info("Buscando Empréstimos por Usuário");
        List<EmprestimoResponseDTO> retorno = service.findByUsuarioId(usuarioId);
        return Response.ok(retorno).build();
    }

    @GET
    @Transactional
    public Response findAll() {
        LOG.info("Listando todos os Empréstimos");
        List<EmprestimoResponseDTO> retorno = service.findAll();
        return Response.ok(retorno).build();
    }
}
