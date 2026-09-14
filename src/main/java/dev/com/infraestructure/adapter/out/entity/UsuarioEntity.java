package dev.com.infraestructure.adapter.out.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Data
public class UsuarioEntity extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario" )
    private Long idUsuario;

    @Column(name = "nombre" )
    private String nombre;

    @Column(name = "apellido" )
    private String apellido;

    @Column(name = "profesion" )
    private String profesion;

    @Column(name = "correo" )
    private String correo;

    @Column(name = "contacto" )
    private String contacto;

    @Column(name = "fecha_nacimiento" )
    private LocalDate fechaCumpleano;

    @Column(name = "clave" )
    private String clave;

    @Column(name = "estado" )
    private Boolean estado;

    @Column(name = "fechaElimina")
    private LocalDateTime fechaEliminacion;
}
