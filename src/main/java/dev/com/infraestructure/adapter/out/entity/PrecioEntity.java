package dev.com.infraestructure.adapter.out.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * <b>
 * Precio Entity.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 14/05/2026 $]
 * </p>
 */
@Entity
@Table(name = "precio")
@Data
public class PrecioEntity extends PanacheEntityBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrecio")
    private Long idPrecio;

    @Column(name = "precio")
    private Float precio;

    @Column(name = "descr_desc")
    private String descripcionDescuento;

    @Column(name = "prcntj_desc")
    private String porcentajeDescuento;

    @Column(name = "estado")
    private Boolean estado;

}
