package dev.com.infraestructure.adapter.out.repository;

import dev.com.infraestructure.adapter.out.entity.CatRolesEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CatRolesRepository implements PanacheRepository<CatRolesEntity> {
}
