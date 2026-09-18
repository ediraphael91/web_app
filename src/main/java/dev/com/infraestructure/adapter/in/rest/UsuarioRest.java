package dev.com.infraestructure.adapter.in.rest;

import dev.com.application.ports.in.UsuarioInPort;
import dev.com.domain.request.UsuarioRequest;
import dev.com.domain.response.PaginaResponse;
import dev.com.domain.response.UsuarioResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioRest {

    private final UsuarioInPort usuarioInPort;
    @Inject
    public UsuarioRest(UsuarioInPort usuarioInPort) {
        this.usuarioInPort = usuarioInPort;
    }

    @POST
    @Path("/crear")
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        return usuarioInPort.crearUsuario(request);
    }

    @PUT
    @Path("/actualizar/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public UsuarioResponse actualizarUsuario(@PathParam("idUsuario") Long idUsuario,
                                             UsuarioRequest request) {
        return usuarioInPort.actualizarUsuario(idUsuario, request);
    }

    @GET
    @Path("/listar")
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioInPort.listarUsuarios();
    }

    @DELETE
    @Path("/eliminar/{idUsuario}")
    public void eliminarUsuario(@PathParam("idUsuario") Long idUsuario) {
        usuarioInPort.eliminarUsuario(idUsuario);
    }

    @GET
    @Path("/listar/paginado")
    public PaginaResponse<UsuarioResponse> listaPagina(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("5") int size) {
        return usuarioInPort.listaPagina(page, size);
    }
}
