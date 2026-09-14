package dev.com.application.ports.out;

import dev.com.domain.entity.PaginasDomain;
import dev.com.domain.entity.UsuarioDomain;

import java.util.List;

public interface UsuarioOutPort {

    UsuarioDomain guardarUsuario(UsuarioDomain usuario);
    //UsuarioDomain buscarPorId(Long idUsuario);
    List<UsuarioDomain> listarUsuarios();
    //void eliminarUsuario(Long idUsuario);
    PaginasDomain<UsuarioDomain> listaUsuarioPag(int page, int size);
}
