package dev.com.infraestructure.adapter.out.repository;

import dev.com.infraestructure.adapter.out.entity.UsuarioRolEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UsuarioRolRepository implements PanacheRepository<UsuarioRolEntity> {

    public Optional<UsuarioRolEntity> buscarPorUsuario(Long idUsuario) {
        return find("usuario.idUsuario", idUsuario).firstResultOptional();
    }
}