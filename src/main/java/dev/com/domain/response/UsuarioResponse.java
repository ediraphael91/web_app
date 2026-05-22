package dev.com.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private Long idusuario;
    private String nombre;
    private String password;
    private String correo;
    private String contacto;
    private String Date;
    private Boolean estado;
}
