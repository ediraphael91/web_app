package dev.com.application.usecase;

import dev.com.application.ports.in.CursoInPort;
import dev.com.application.ports.out.CursoOutPort;
import dev.com.application.ports.out.ImagenOutPort;
import dev.com.domain.entity.CursoDomain;
import dev.com.domain.request.CursoRequest;
import dev.com.domain.response.CursoResponse;
import dev.com.infraestructure.adapter.out.repository.mapper.CursoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.IOException;
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
    private final ImagenOutPort imagenOutPort;


    public CursoUseCase(CursoOutPort cursoOutPort, ImagenOutPort imagenOutPort){
        this.cursoOutPort = cursoOutPort;
        this.imagenOutPort = imagenOutPort;

    }

    @Override
    public CursoResponse crearCurso(CursoRequest request, FileUpload imagen){
        try {
            if (imagen != null) {
                String nombreImagen = imagenOutPort.guardar(imagen);
                request.setImagen(nombreImagen);
            }
        CursoDomain domain = CursoMapper.toDomain(request);
        CursoDomain saved = cursoOutPort.guardarCurso(domain);
        return CursoMapper.toResponse(saved);
    } catch (IOException e) {
            throw new RuntimeException("Error al gaurdar la imagen del curso", e);
        }
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
