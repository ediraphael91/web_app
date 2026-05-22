package dev.com.infraestructure.adapter;

import dev.com.application.ports.out.CursoOutPort;
import dev.com.domain.entity.CursoDomain;
import dev.com.domain.request.CursoRequest;
import dev.com.domain.response.CursoResponse;
import dev.com.infraestructure.adapter.out.repository.mapper.CursoMapper;

import java.util.List;
import java.util.stream.Collectors;

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
public class CursoAdapter {

    private final CursoOutPort cursoOutPort;

    public CursoAdapter(CursoOutPort cursoOutPort){
        this.cursoOutPort = cursoOutPort;
    }

    public CursoResponse obtenerCursoporId(Long idCurso){
        CursoDomain domain = cursoOutPort.buscarPorId(idCurso);
        return CursoMapper.toResponse(domain);
    }

    public List<CursoResponse> obtenerCursos(){
        List<CursoDomain> resultados = cursoOutPort.listarCursos();
        return resultados.stream()
                .map(CursoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CursoResponse guardarCurso(CursoRequest request){
        CursoDomain domain = CursoMapper.toDomain(request);
        CursoDomain saved = cursoOutPort.guardarCurso(domain);
        return CursoMapper.toResponse(saved);
    }

    public void eliminarCurso(Long idCurso){
        cursoOutPort.eliminar(idCurso);
    }
}
