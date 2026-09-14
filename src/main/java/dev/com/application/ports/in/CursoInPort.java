package dev.com.application.ports.in;

import dev.com.domain.request.CursoRequest;
import dev.com.domain.response.CursoResponse;
import dev.com.domain.response.PaginaResponse;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.util.List;

/**
 * <b>
 * Curso InPort.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 07/05/2026 $]
 * </p>
 */
public interface CursoInPort {

    CursoResponse crearCurso(CursoRequest request, FileUpload imagen);
    CursoResponse actualizarCurso(Long idCurso, CursoRequest request, FileUpload imagen);
    void eliminarCurso(Long idCurso);
    List<CursoResponse> listarCursos();
    CursoResponse buscarPorId(Long idCurso);
    PaginaResponse<CursoResponse> listaCursoPag(int page, int size);
}
