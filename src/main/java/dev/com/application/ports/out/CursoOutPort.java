package dev.com.application.ports.out;

import dev.com.domain.entity.CursoDomain;
import dev.com.domain.entity.PaginasDomain;

import java.util.List;

/**
 * <b>
 * Curso OutPort.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 30/03/2026 $]
 * </p>
 */
public interface CursoOutPort {

    CursoDomain guardarCurso(CursoDomain curso);
    CursoDomain buscarPorId(Long idCurso);
    List<CursoDomain> listarCursos();
    void eliminar (Long idCurso);
    PaginasDomain<CursoDomain> listaCursoPag(int page, int size);
}
