package dev.com.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String profesion;
    private String correo;
    private String contacto;
    private LocalDate fechaCumpleano;
    private String clave;
    private Boolean estado;
    private LocalDateTime fechaEliminacion;
    private LocalDateTime fechaModificacion;
}
