package dev.com.infraestructure.adapter.in.rest;

import dev.com.application.ports.in.UsuarioInPort;
import dev.com.domain.request.UsuarioRequest;
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

    @GET
    @Path("/listar")
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioInPort.listarUsuarios();
    }
}
