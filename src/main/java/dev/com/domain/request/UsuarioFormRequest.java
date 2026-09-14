package dev.com.domain.request;

import lombok.Data;
import org.jboss.resteasy.reactive.RestForm;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class UsuarioFormRequest {

    @RestForm("nombre")
    private String nombre;

    @RestForm("apellido")
    private String apellido;

    @RestForm("profesion")
    private String profesion;

    @RestForm("correo")
    private String correo;

    @RestForm("contacto")
    private String contacto;

    @RestForm("fechaCumpleano")
    private Date fechaCumpleano;

    @RestForm("clave")
    private String clave;

    @RestForm("estado")
    private Boolean estado;

    @RestForm("fechaEliminacion")
    private LocalDateTime fechaEliminacion;
}
