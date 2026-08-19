package dev.com.infraestructure.adapter;

import dev.com.application.ports.out.CursoOutPort;
import dev.com.domain.entity.CursoDomain;
import dev.com.infraestructure.adapter.out.entity.CursoEntity;
import dev.com.infraestructure.adapter.out.repository.CursoRepository;
import dev.com.infraestructure.adapter.out.repository.mapper.CursoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

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

        CursoEntity entity = CursoMapper.toEntity(curso);

        entity = repository.getEntityManager().merge(entity);

        return CursoMapper.toDomain(entity);
    }

    @Override
    public CursoDomain buscarPorId(Long idCurso) {

        CursoEntity entity = repository.findById(idCurso);

        return CursoMapper.toDomain(entity);
    }

    @Override
    public List<CursoDomain> listarCursos() {

        return CursoMapper.toDomainList(repository.listAll());
    }

    @Override
    @Transactional
    public void eliminar(Long idCurso) {

        repository.deleteById(idCurso);
    }


}
