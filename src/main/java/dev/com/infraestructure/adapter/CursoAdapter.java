package dev.com.infraestructure.adapter;

import dev.com.application.ports.out.CursoOutPort;
import dev.com.domain.entity.CursoDomain;
import dev.com.domain.entity.PaginasDomain;
import dev.com.infraestructure.adapter.out.entity.CursoEntity;
import dev.com.infraestructure.adapter.out.repository.CursoRepository;
import dev.com.infraestructure.adapter.out.repository.mapper.CursoMapper;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <b>
 * Curso Adapter.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 30/03/2026 $]
 * </p>
 */
@ApplicationScoped
@AllArgsConstructor
public class CursoAdapter implements CursoOutPort {

    private final CursoRepository repository;

    @Override
    @Transactional
    public CursoDomain guardarCurso(CursoDomain curso) {
        CursoEntity entity;
        if (curso.getIdCurso() != null) {
            entity = repository.findById(curso.getIdCurso());
            if (entity == null) {
                throw new IllegalArgumentException("Id de Curso no encontrado." + curso.getIdCurso());
            }
            entity.setNombre(curso.getNombre());
            entity.setDescripcion(curso.getDescripcion());
            entity.setPrecio(curso.getPrecio());
            entity.setCategoria(curso.getCategoria());
            entity.setModalidad(curso.getModalidad());
            entity.setFechaInicio(curso.getFechaInicio());
            entity.setFechaFin(curso.getFechaFin());
            entity.setImagen(curso.getImagen());
            entity.setEstado(curso.getEstado());
        } else {
            entity = CursoMapper.toEntity(curso);
            repository.persist(entity);
        }
        return CursoMapper.toDomain(entity);
    }

    @Override
    public CursoDomain buscarPorId(Long idCurso) {

        CursoEntity entity = repository.findById(idCurso);

        return CursoMapper.toDomain(entity);
    }

    @Override
    public List<CursoDomain> listarCursos() {
        return CursoMapper.toDomainList(repository.list("estado", true));
    }

    @Override
    @Transactional
    public void eliminar(Long idCurso) {
        CursoEntity entity = repository.findById(idCurso);
        if (entity == null) {
            throw new IllegalArgumentException("Curso no encontrado Id: " + idCurso);
        }
        entity.setEstado(false);
        entity.setFechaEliminacion(LocalDateTime.now());
        entity.persist();
    }

    @Override
    public PaginasDomain<CursoDomain> listaPagina(int page, int size) {
        PanacheQuery<CursoEntity> query = repository.paginarActivos(page, size);

        List<CursoDomain> contenido = CursoMapper.toDomainList(query.list());
        long totalElementos = query.count();
        int totalPaginas = query.pageCount();

        return PaginasDomain.<CursoDomain>builder()
                .contenido(contenido)
                .totalElementos(totalElementos)
                .totalPaginas(totalPaginas)
                .paginaActual(page)
                .tamanoPAgina(size)
                .build();
    }


}
