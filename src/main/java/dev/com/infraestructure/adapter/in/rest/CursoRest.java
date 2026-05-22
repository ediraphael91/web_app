package dev.com.infraestructure.adapter.in.rest;

import dev.com.application.ports.in.CursoInPort;
import dev.com.domain.request.CursoRequest;
import dev.com.domain.response.CursoResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * <b>
 * Curso Rest.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 07/05/2026 $]
 * </p>
 */
@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoRest {

    private final CursoInPort cursoInPort;

    @Inject
    public CursoRest(CursoInPort cursoInPort){
        this.cursoInPort = cursoInPort;
    }

    @POST
    @Path("/crear")
    public CursoResponse crearCurso(CursoRequest request){
        return cursoInPort.crearCurso(request);
    }

    @PUT
    @Path("/actualizar/{idCurso}")
    public CursoResponse actualizarCurso(@PathParam("idCurso") Long idCurso, CursoRequest request){
        return cursoInPort.actualizarCurso(idCurso, request);
    }

    @DELETE
    @Path("/eliminar/{idCurso}")
    public void eliminarCurso(@PathParam("idCurso") Long idCurso){
        cursoInPort.eliminarCurso(idCurso);
    }

    @GET
    @Path("/listar")
    public List<CursoResponse> listarCursos(){
        return cursoInPort.listarCursos();
    }

    @GET
    @Path("/listar/{idCurso}")
    public Response buscarPorId(@PathParam("idCurso") Long idCurso){
        CursoResponse curso = cursoInPort.buscarPorId(idCurso);
        if (curso == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(curso).build();
    }
}
