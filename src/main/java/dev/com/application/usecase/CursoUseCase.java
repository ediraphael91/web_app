package dev.com.application.usecase;

import dev.com.application.ports.in.CursoInPort;
import dev.com.application.ports.out.CursoOutPort;
import dev.com.application.ports.out.ImagenOutPort;
import dev.com.domain.entity.CursoDomain;
import dev.com.domain.entity.PaginasDomain;
import dev.com.domain.request.CursoRequest;
import dev.com.domain.response.CursoResponse;
import dev.com.domain.response.PaginaResponse;
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


    public CursoUseCase(CursoOutPort cursoOutPort, ImagenOutPort imagenOutPort) {
        this.cursoOutPort = cursoOutPort;
        this.imagenOutPort = imagenOutPort;

    }

    @Override
    public CursoResponse crearCurso(CursoRequest request, FileUpload imagen) {
        try {
            if (imagen != null) {
                String nombreImagen = imagenOutPort.guardar(imagen);
                request.setImagen(nombreImagen);
            }
            CursoDomain domain = CursoMapper.toDomain(request);
            CursoDomain saved = cursoOutPort.guardarCurso(domain);
            return CursoMapper.toResponse(saved);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen del curso", e);
        }
    }

    @Override
    public CursoResponse actualizarCurso(Long idCurso, CursoRequest request, FileUpload imagen) {
        try {
            if (imagen != null) {
                // Llega imagen nueva -> se guarda y se reemplaza el nombre
                String nombreImagen = imagenOutPort.guardar(imagen);
                request.setImagen(nombreImagen);
            } else {
                // No llega imagen -> conservar la que ya existía en BD
                CursoDomain existente = cursoOutPort.buscarPorId(idCurso);
                if (existente == null) {
                    throw new RuntimeException("Curso no encontrado con Id: " + idCurso);
                }
                request.setImagen(existente.getImagen());
            }

        CursoDomain domain = CursoMapper.toDomain(request);
        domain.setIdCurso(idCurso);
        CursoDomain update = cursoOutPort.guardarCurso(domain);
        return CursoMapper.toResponse(update);

        } catch(IOException e) {
            throw new RuntimeException("Error al guardar la imagen del curso", e);
            }
    }


    @Override
    public void eliminarCurso(Long idCurso) {
        cursoOutPort.eliminar(idCurso);
    }

    @Override
    public List<CursoResponse> listarCursos() {
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

    @Override
    public PaginaResponse<CursoResponse> listaPagina(int page, int size) {
        PaginasDomain<CursoDomain> pagina = cursoOutPort.listaPagina(page, size);
        List<CursoResponse> contenido = CursoMapper.toResponseList(pagina.getContenido());
        return PaginaResponse.<CursoResponse>builder()
                .contenido(contenido)
                .totalElementos(pagina.getTotalElementos())
                .totalPaginas(pagina.getTotalPaginas())
                .paginaActual(pagina.getPaginaActual())
                .tamanoPagina(pagina.getTamanoPAgina())
                .build();
    }
}
