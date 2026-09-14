package dev.com.infraestructure.adapter.out.repository;

import dev.com.infraestructure.adapter.out.entity.UsuarioEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PaginableRepository<UsuarioEntity> {
    // paginar() y paginarActivos() ya vienen incluidos
}
