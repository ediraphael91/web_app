package dev.com.infraestructure.adapter.out.repository.mapper;

import dev.com.domain.entity.CursoDomain;
import dev.com.domain.request.CursoRequest;
import dev.com.domain.response.CursoResponse;
import dev.com.infraestructure.adapter.out.entity.CursoEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>
 * Curso Mapper.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 07/05/2026 $]
 * </p>
 */
public final class CursoMapper {

    private CursoMapper(){
        throw new UnsupportedOperationException("Clase utilitaria");
    }

    /**
     * Convierte un CursoEntity a CursoDomain.
     */
    public static CursoDomain toDomain(CursoEntity entity){
        if (entity == null) return null;
        return new CursoDomain(
                entity.getIdCurso(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecio(),
                entity.getCategoria(),
                entity.getModalidad(),
                entity.getFechaInicio(),
                entity.getFechaFin(),
                entity.getImagen(),
                entity.getEstado()
        );
    }

    /**
     * Convierte un CursoDomain a CursoEntity.
     */
    public static CursoEntity toEntity(CursoDomain domain){
        if (domain == null) return  null;

        CursoEntity entity = new CursoEntity();
        entity.setIdCurso((domain.getIdCurso()));
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(domain.getDescripcion());
        entity.setPrecio(domain.getPrecio());
        entity.setCategoria(domain.getCategoria());
        entity.setModalidad(domain.getModalidad());
        entity.setFechaInicio(domain.getFechaInicio());
        entity.setFechaFin(domain.getFechaFin());
        entity.setImagen(domain.getImagen());
        entity.setEstado(domain.getEstado());
        return entity;
    }

    /**
     * Convierte un CursoRequest a CursoDomain.
     */
    public static CursoDomain toDomain(CursoRequest request){
        if (request == null) return null;
        return new CursoDomain(
                null, // idCurso se asigna en el caso de uso
                request.getNombre(),
                request.getDescripcion(),
                request.getPrecio(),
                request.getCategoria(),
                request.getModalidad(),
                request.getFechaInicio(),
                request.getFechaFin(),
                request.getImagen(),
                request.getEstado()
        );
    }

    /**
     * Convierte un CursoDomain a CursoResponse.
     */
    public static CursoResponse toResponse(CursoDomain domain){
        if (domain == null) return null;
        return new CursoResponse(
                domain.getIdCurso(),
                domain.getNombre(),
                domain.getDescripcion(),
                domain.getPrecio(),
                domain.getCategoria(),
                domain.getModalidad(),
                domain.getFechaInicio(),
                domain.getFechaFin(),
                domain.getImagen(),
                domain.getEstado()
        );
    }

    /**
     * Convierte una lista de CursoEntity a lista de CursoDomain.
     */
    public static List<CursoDomain> toDomainList(List<CursoEntity> entities) {
        return entities.stream()
                .map(CursoMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Convierte una lista de CursoDomain a lista de CursoResponse.
     */
    public static List<CursoResponse> toResponseList(List<CursoDomain> domains) {
        return domains.stream()
                .map(CursoMapper::toResponse)
                .collect(Collectors.toList());
    }
}
