package dev.com.application.ports.in;

import dev.com.domain.entity.CatRolesDomain;
import dev.com.infraestructure.adapter.in.response.CatRolesResponse;

import java.util.List;

public interface CatRolInPort {

    List<CatRolesDomain> listarRoles();
}
