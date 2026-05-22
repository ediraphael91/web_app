package dev.com.infraestructure.adapter.out.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "rol")
@Data
public class RolEntity extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol" )
    private Long idRol;

    @Column(name = "nombre" )
    private String nombre;

    @Column(name = "descripcion" )
    private String descripcion;

    @Column(name = "estado" )
    private Boolean  estado;
}
