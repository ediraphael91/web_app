package dev.com.domain.request;

import org.jboss.resteasy.reactive.multipart.FileUpload;
import lombok.Data;
import org.jboss.resteasy.reactive.RestForm;


import java.time.LocalDate;

@Data
public class CursoFormRequest {

    @RestForm("nombre")
    private String nombre;

    @RestForm("descripcion")
    private String descripcion;

    @RestForm("precio")
    private Double precio;

    @RestForm("categoria")
    private String categoria;

    @RestForm("modalidad")
    private String modalidad;

    @RestForm("fechaInicio")
    private LocalDate fechaInicio;

    @RestForm("fechaFin")
    private LocalDate fechaFin;

    @RestForm("estado")
    private Boolean estado;

    @RestForm("imagen")
    private FileUpload imagen;
}
