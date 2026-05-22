package dev.com.infraestructure.adapter.out.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "usuario")
@Data
public class UsuarioEntity extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario" )
    private Long idusuario;

    @Column(name = "nombre" )
    private String nombre;

    @Column(name = "apellido" )
    private String password;

    @Column(name = "correo" )
    private String correo;

    @Column(name = "contacto" )
    private String contacto;

    @Column(name = "fecha_nacimineto" )
    private String Date;

    @Column(name = "estado" )
    private Boolean estado;
}
