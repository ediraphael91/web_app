package dev.com.application.ports.in;

import dev.com.domain.request.CursoRequest;
import dev.com.domain.request.UsuarioRequest;
import dev.com.domain.response.CursoResponse;
import dev.com.domain.response.UsuarioResponse;

public interface UsuarioInPort {

    UsuarioResponse crearUsuario(UsuarioRequest request);
}
