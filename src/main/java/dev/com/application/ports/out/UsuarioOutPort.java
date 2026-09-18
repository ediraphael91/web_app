package dev.com.application.ports.out;

import dev.com.domain.entity.PaginasDomain;
import dev.com.domain.entity.UsuarioDomain;

import java.util.List;

public interface UsuarioOutPort {

    UsuarioDomain guardarUsuario(UsuarioDomain usuario);
    List<UsuarioDomain> listarUsuarios();
    void eliminar(Long idUsuario);
    PaginasDomain<UsuarioDomain> listaPagina(int page, int size);
}
