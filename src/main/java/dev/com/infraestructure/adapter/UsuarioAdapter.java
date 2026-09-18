package dev.com.infraestructure.adapter;

import dev.com.application.ports.out.UsuarioOutPort;
import dev.com.domain.entity.PaginasDomain;
import dev.com.domain.entity.UsuarioDomain;
import dev.com.infraestructure.adapter.out.entity.UsuarioEntity;
import dev.com.infraestructure.adapter.out.repository.UsuarioRepository;
import dev.com.infraestructure.adapter.out.repository.mapper.UsuarioMapper;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class UsuarioAdapter implements UsuarioOutPort {

    private final UsuarioRepository repoUsuario;

    @Override
    @Transactional
    public UsuarioDomain guardarUsuario(UsuarioDomain usuario) {
        UsuarioEntity entity;
        if (usuario.getIdUsuario() != null) {
            entity = repoUsuario.findById(usuario.getIdUsuario());
            if (entity == null) {
                throw new IllegalArgumentException("Usuario no encontrrado Id:" + usuario.getIdUsuario());
            }
            entity.setNombre(usuario.getNombre());
            entity.setApellido(usuario.getApellido());
            entity.setProfesion(usuario.getProfesion());
            entity.setCorreo(usuario.getCorreo());
            entity.setContacto(usuario.getContacto());
            entity.setFechaCumpleano(usuario.getFechaCumpleano());
            entity.setEstado(usuario.getEstado());
            entity.setFechaModificacion(LocalDateTime.now());

            // Actualizar si viene una nueva
            if (usuario.getClave() != null && !usuario.getClave().isBlank()) {
                entity.setClave(usuario.getClave());
            }
        } else {
            entity = UsuarioMapper.toEntity(usuario);
            repoUsuario.persist(entity);
        }
        return UsuarioMapper.toDomain(entity);

    }

    @Override
    public List<UsuarioDomain> listarUsuarios() {
        return UsuarioMapper.toDomainList(repoUsuario.list("estado", true));
    }

    @Override
    public PaginasDomain<UsuarioDomain> listaPagina(int page, int size) {
        PanacheQuery<UsuarioEntity> query = repoUsuario.paginarActivos(page, size);

        List<UsuarioDomain> contenido = UsuarioMapper.toDomainList(query.list());
        long totalElementos = query.count();
        int totalPaginas = query.pageCount();

        return PaginasDomain.<UsuarioDomain>builder()
                .contenido(contenido)
                .totalElementos(totalElementos)
                .totalPaginas(totalPaginas)
                .paginaActual(page)
                .tamanoPAgina(size)
                .build();
    }

    @Override
    @Transactional
    public void eliminar(Long idUsuario) {
        UsuarioEntity entity = repoUsuario.findById(idUsuario);
        if (entity == null) {
            throw new IllegalArgumentException("Usuario no encontrado Id: " + idUsuario);
        }
        entity.setEstado(false);
        entity.setFechaEliminacion(LocalDateTime.now());
        entity.persist();
    }
}


