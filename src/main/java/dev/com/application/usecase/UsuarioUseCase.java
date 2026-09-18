package dev.com.application.usecase;

import dev.com.application.ports.in.UsuarioInPort;
import dev.com.application.ports.out.UsuarioOutPort;
import dev.com.domain.entity.PaginasDomain;
import dev.com.domain.entity.UsuarioDomain;
import dev.com.domain.request.UsuarioRequest;
import dev.com.domain.response.PaginaResponse;
import dev.com.domain.response.UsuarioResponse;
import dev.com.infraestructure.adapter.out.repository.mapper.UsuarioMapper;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioUseCase implements UsuarioInPort {

    private final UsuarioOutPort usuarioOutPort;

    public UsuarioUseCase(UsuarioOutPort usuarioOutPort){
        this.usuarioOutPort = usuarioOutPort;
    }

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request) {

            UsuarioDomain domain = UsuarioMapper.toDomain(request);
            UsuarioDomain saved = usuarioOutPort.guardarUsuario(domain);
            return UsuarioMapper.toResponse(saved);
    }

    @Override
    public UsuarioResponse actualizarUsuario(Long idUsuario, UsuarioRequest request) {
        UsuarioDomain domain = UsuarioMapper.toDomain(request);
        domain.setIdUsuario(idUsuario);
        UsuarioDomain update = usuarioOutPort.guardarUsuario(domain);
        return UsuarioMapper.toResponse(update);
    }

    @Override
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioOutPort.listarUsuarios()
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaginaResponse<UsuarioResponse> listaPagina(int page, int size) {
        PaginasDomain<UsuarioDomain> pagina = usuarioOutPort.listaPagina(page, size);
        List<UsuarioResponse> contenido = UsuarioMapper.toResponseList(pagina.getContenido());
        return PaginaResponse.<UsuarioResponse>builder()
                .contenido(contenido)
                .totalElementos(pagina.getTotalElementos())
                .totalPaginas(pagina.getTotalPaginas())
                .paginaActual(pagina.getPaginaActual())
                .tamanoPagina(pagina.getTamanoPAgina())
                .build();
    }

    @Override
    public void eliminarUsuario(Long idUsuario) {
        usuarioOutPort.eliminar(idUsuario);
    }

}
