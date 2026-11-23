package dev.com.infraestructure.adapter;

import dev.com.application.ports.out.UsuarioOutPort;
import dev.com.domain.entity.PaginasDomain;
import dev.com.domain.entity.UsuarioDomain;
import dev.com.infraestructure.adapter.out.entity.CatRolesEntity;
import dev.com.infraestructure.adapter.out.entity.UsuarioEntity;
import dev.com.infraestructure.adapter.out.entity.UsuarioRolEntity;
import dev.com.infraestructure.adapter.out.repository.CatRolesRepository;
import dev.com.infraestructure.adapter.out.repository.UsuarioRepository;
import dev.com.infraestructure.adapter.out.repository.UsuarioRolRepository;
import dev.com.infraestructure.adapter.out.repository.mapper.UsuarioMapper;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class UsuarioAdapter implements UsuarioOutPort {

    private final UsuarioRepository repoUsuario;
    private final UsuarioRolRepository repoUsuarioRol;
    private final CatRolesRepository repoCatRoles;

    @Override
    @Transactional
    public UsuarioDomain guardarUsuario(UsuarioDomain usuario) {
        UsuarioEntity entity;
        boolean esNuevo = usuario.getIdUsuario() == null;

        if (!esNuevo) {
            entity = repoUsuario.findById(usuario.getIdUsuario());
            if (entity == null) {
                throw new IllegalArgumentException("Usuario no encontrado Id:" + usuario.getIdUsuario());
            }
            entity.setNombre(usuario.getNombre());
            entity.setApellido(usuario.getApellido());
            entity.setProfesion(usuario.getProfesion());
            entity.setCorreo(usuario.getCorreo());
            entity.setContacto(usuario.getContacto());
            entity.setFechaCumpleano(usuario.getFechaCumpleano());
            entity.setEstado(usuario.getEstado());
            entity.setFechaModificacion(LocalDateTime.now());

            if (usuario.getClave() != null && !usuario.getClave().isBlank()) {
                entity.setClave(usuario.getClave());
            }
        } else {
            entity = UsuarioMapper.toEntity(usuario);
            entity.setFechaCreacion(LocalDateTime.now());
            repoUsuario.persist(entity);
        }

        asignarRol(entity, usuario.getIdRol());

        UsuarioRolEntity usuarioRol = repoUsuarioRol.buscarPorUsuario(entity.getIdUsuario()).orElse(null);
        return UsuarioMapper.toDomain(entity, usuarioRol);
    }

    /**
     * Crea o reemplaza la relación usuario-rol para el usuario dado.
     */
    private void asignarRol(UsuarioEntity usuarioEntity, Long idRol) {
        if (idRol == null) return;

        CatRolesEntity rol = repoCatRoles.findById(idRol);
        if (rol == null) {
            throw new IllegalArgumentException("Rol no encontrado Id: " + idRol);
        }

        UsuarioRolEntity usuarioRol = repoUsuarioRol.buscarPorUsuario(usuarioEntity.getIdUsuario())
                .orElseGet(UsuarioRolEntity::new);

        usuarioRol.setUsuario(usuarioEntity);
        usuarioRol.setRol(rol);

        if (usuarioRol.getIdUsuarioRol() == null) {
            repoUsuarioRol.persist(usuarioRol);
        }
    }

    @Override
    public List<UsuarioDomain> listarUsuarios() {
        return repoUsuario.list("estado", true).stream()
                .map(e -> UsuarioMapper.toDomain(e, repoUsuarioRol.buscarPorUsuario(e.getIdUsuario()).orElse(null)))
                .collect(Collectors.toList());
    }

    @Override
    public PaginasDomain<UsuarioDomain> listaPagina(int page, int size) {
        PanacheQuery<UsuarioEntity> query = repoUsuario.paginarActivos(page, size);

        List<UsuarioDomain> contenido = query.list().stream()
                .map(e -> UsuarioMapper.toDomain(e, repoUsuarioRol.buscarPorUsuario(e.getIdUsuario()).orElse(null)))
                .collect(Collectors.toList());
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