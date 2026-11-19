package dev.com.infraestructure.adapter.out.repository;

import dev.com.application.ports.out.CatRolOutPort;
import dev.com.domain.entity.CatRolesDomain;
import dev.com.infraestructure.adapter.out.entity.CatRolesEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CatRolesRepository implements PanacheRepository<CatRolesEntity>, CatRolOutPort {

    @Override
    public List<CatRolesDomain> listarRoles() {

        return listAll()
                .stream()
                .map(rol -> new CatRolesDomain(
                        rol.getIdRol(),
                        rol.getNombre(),
                        rol.getDescripcion(),
                        rol.getEstado()
                ))
                .toList();
    }

}
