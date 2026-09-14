package dev.com.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * <b>
 * Curso Response.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 07/05/2026 $]
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoResponse {

    private Long idCurso;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private String modalidad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String imagen;
    private Boolean estado;
    private LocalDateTime fechaEliminacion;
}
