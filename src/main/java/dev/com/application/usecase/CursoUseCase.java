package dev.com.application.usecase;

import dev.com.application.ports.in.CursoInPort;
import dev.com.application.ports.out.CursoOutPort;
import dev.com.domain.entity.CursoDomain;
import dev.com.domain.request.CursoRequest;
import dev.com.domain.response.CursoResponse;
import dev.com.infraestructure.adapter.out.repository.mapper.CursoMapper;
import jakarta.enterprise.context.ApplicationScoped;


import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>
 * Curso UseCase.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 07/05/2026 $]
 * </p>
 */
@ApplicationScoped
public class CursoUseCase implements CursoInPort {

    private final CursoOutPort cursoOutPort;


    public CursoUseCase(CursoOutPort cursoOutPort){
        this.cursoOutPort = cursoOutPort;

    }

    @Override
    public CursoResponse crearCurso(CursoRequest request){
        CursoDomain domain = CursoMapper.toDomain(request);
        CursoDomain saved = cursoOutPort.guardarCurso(domain);
        return CursoMapper.toResponse(saved);
    }

    @Override
    public CursoResponse actualizarCurso(Long idCurso, CursoRequest request){
        CursoDomain domain = CursoMapper.toDomain(request);
        domain.setIdCurso(idCurso);
        CursoDomain update = cursoOutPort.guardarCurso(domain);
        return CursoMapper.toResponse(update);
    }

    @Override
    public void eliminarCurso(Long idCurso){
        cursoOutPort.eliminar(idCurso);
    }

    @Override
    public List<CursoResponse> listarCursos(){
        return cursoOutPort.listarCursos()
                .stream()
                .map(CursoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CursoResponse buscarPorId(Long idCurso) {
        CursoDomain domain = cursoOutPort.buscarPorId(idCurso);
        return domain != null ? CursoMapper.toResponse(domain) : null;
    }
}
