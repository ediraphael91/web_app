package dev.com.infraestructure.adapter.out.repository;

import dev.com.application.ports.out.CursoOutPort;
import dev.com.domain.entity.CursoDomain;
import dev.com.infraestructure.adapter.out.entity.CursoEntity;
import dev.com.infraestructure.adapter.out.repository.mapper.CursoMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>
 * Curso Repository.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 07/05/2026 $]
 * </p>
 */
@ApplicationScoped
public class CursoRepository implements CursoOutPort, PanacheRepository<CursoEntity> {

    @Override
    @Transactional
    public CursoDomain guardarCurso(CursoDomain curso){
        CursoEntity entity = CursoMapper.toEntity(curso);
        entity = getEntityManager().merge(entity); // merge sirve tanto para insertar como actualizar
        return CursoMapper.toDomain(entity);
    }

    @Override
    public CursoDomain buscarPorId(Long idCurso){
        CursoEntity entity = findById(idCurso);
        return entity != null? toDomain(entity) : null;
    }

    @Override
    public List<CursoDomain> listarCursos(){
        return listAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void eliminar(Long idCurso){
        deleteById(idCurso);
    }

    //Convertir Entity a Domain
    private CursoEntity toEntity(CursoDomain domain){
        CursoEntity entity = new CursoEntity();
        entity.setIdCurso(domain.getIdCurso());
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

    private CursoDomain toDomain(CursoEntity entity){
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
}
