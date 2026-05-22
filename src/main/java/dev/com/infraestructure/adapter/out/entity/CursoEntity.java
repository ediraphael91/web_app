package dev.com.infraestructure.adapter.out.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>
 * Curso Entity.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 30/04/2026 $]
 * </p>
 */
@Entity
@Table(name = "curso")
@Data
public class CursoEntity extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso" )
    private Long idCurso;

    @Column(name = "nombre" )
    private String nombre;

    @Column(name = "descripcion" )
    private String descripcion;

    @Column(name = "precio" )
    private Double precio;

    @Column(name = "categoria" )
    private String categoria;

    @Column(name = "modalidad" )
    private String modalidad;

    @Column(name = "fechaInicio" )
    private Date fechaInicio;

    @Column(name = "fechafin" )
    private Date fechaFin;

    @Column(name = "imagen" )
    private String imagen;

    @Column(name = "estado" )
    private Boolean  estado;
}
