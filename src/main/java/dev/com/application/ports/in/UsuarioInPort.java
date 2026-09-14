package dev.com.application.ports.in;

import dev.com.domain.request.UsuarioRequest;
import dev.com.domain.response.PaginaResponse;
import dev.com.domain.response.UsuarioResponse;

import java.util.List;

public interface UsuarioInPort {

    UsuarioResponse crearUsuario(UsuarioRequest request);
    //UsuarioResponse actualizarUsuario(Long idUsuario, UsuarioRequest request);
    //void eliminarUsuario(Long idUsuario);
    List<UsuarioResponse> listarUsuarios();
    //CursoResponse buscarPorId(Long idUsuario);
    PaginaResponse<UsuarioResponse> listaUsuarioPag(int page, int size);

}
