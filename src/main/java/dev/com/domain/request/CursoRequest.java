package dev.com.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * <b>
 * Curso Request.
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
public class CursoRequest {
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private String modalidad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String imagen;
    private Boolean estado;
}
