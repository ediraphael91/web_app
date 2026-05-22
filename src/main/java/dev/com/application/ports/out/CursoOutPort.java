package dev.com.application.ports.out;

import dev.com.domain.entity.CursoDomain;

import java.util.List;

/**
 * <b>
 * Credito Host InPort.
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
}
