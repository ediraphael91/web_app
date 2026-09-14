package dev.com.infraestructure.adapter.out.repository;


import dev.com.infraestructure.adapter.out.entity.CursoEntity;

import jakarta.enterprise.context.ApplicationScoped;




/**
 * <b>
 * Curso Repository.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 07/05/2026 $]
 * </p>
 */
@ApplicationScoped
public class CursoRepository implements PaginableRepository<CursoEntity> {
    // paginar() y paginarActivos() ya vienen incluidos
}
