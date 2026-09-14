package dev.com.infraestructure.adapter.out.repository.mapper;

import dev.com.domain.entity.UsuarioDomain;
import dev.com.domain.request.UsuarioRequest;
import dev.com.domain.response.UsuarioResponse;
import dev.com.infraestructure.adapter.out.entity.UsuarioEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    private UsuarioMapper() { throw new UnsupportedOperationException("Clase utilitaria");}

    /**
     * Convierte un UsuarioEntity a UsuarioDomain.
     */
    public static UsuarioDomain toDomain(UsuarioEntity entity) {
        if (entity == null) return null;
        return new UsuarioDomain(
                entity.getIdUsuario(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getProfesion(),
                entity.getCorreo(),
                entity.getContacto(),
                entity.getFechaCumpleano(),
                entity.getClave(),
                entity.getEstado(),
                entity.getFechaEliminacion()
        );
    }

    /**
     * Convierte un UsuarioDomain a Usuarioentity.
     */
    public static UsuarioEntity toEntity(UsuarioDomain domain) {
        if (domain == null) return null;

        UsuarioEntity entity = new UsuarioEntity();
        entity.setIdUsuario((domain.getIdUsuario()));
        entity.setNombre(domain.getNombre());
        entity.setApellido(domain.getApellido());
        entity.setProfesion(domain.getProfesion());
        entity.setCorreo(domain.getCorreo());
        entity.setContacto(domain.getContacto());
        entity.setFechaCumpleano(domain.getFechaCumpleano());
        entity.setClave(domain.getClave());
        entity.setEstado(domain.getEstado());
        entity.setFechaEliminacion(domain.getFechaEliminacion());
        return entity;
    }

    /**
     * Convierte un UsuarioRequest a Usuario Domain.
     */
    public static UsuarioDomain toDomain(UsuarioRequest request){
        if (request == null) return  null;
        return new UsuarioDomain(
                null, // iUsuario se asigna en el caso de uso
                request.getNombre(),
                request.getApellido(),
                request.getProfesion(),
                request.getCorreo(),
                request.getContacto(),
                request.getFechaCumpleano(),
                request.getClave(),
                request.getEstado(),
                request.getFechaEliminacion()
        );
    }

    /**
     * Convierte un UsuarioDomain a UsuarioResponse.
     */
    public static UsuarioResponse toResponse(UsuarioDomain domain){
        if (domain == null) return null;
        return new UsuarioResponse(
        domain.getIdUsuario(),
        domain.getNombre(),
        domain.getApellido(),
        domain.getProfesion(),
        domain.getCorreo(),
        domain.getContacto(),
        domain.getFechaCumpleano(),
        domain.getClave(),
        domain.getEstado(),
        domain.getFechaEliminacion()
        );
    }

    /**
     * Convierte una lista de UsuarioEntity a lista de UsuarioDomain.
     */
    public static List<UsuarioDomain> toDomainList(List<UsuarioEntity> entities) {
        return entities.stream()
                .map(UsuarioMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Convierte una lista de UsuarioDomain a lista de UsuarioResponse.
     */
    public static List<UsuarioResponse> toResponseList(List<UsuarioDomain> domains) {
        return domains.stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }
}
