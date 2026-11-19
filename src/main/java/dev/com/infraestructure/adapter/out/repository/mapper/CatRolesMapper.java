package dev.com.infraestructure.adapter.out.repository.mapper;

import dev.com.domain.entity.CatRolesDomain;
import dev.com.infraestructure.adapter.in.response.CatRolesResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CatRolesMapper {

    public CatRolesResponse toResponse(CatRolesDomain domain) {

        return new CatRolesResponse(
                domain.idRol(),
                domain.nombre(),
                domain.descripcion(),
                domain.estado()
        );
    }
}
