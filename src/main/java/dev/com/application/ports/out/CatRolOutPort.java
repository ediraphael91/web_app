package dev.com.application.ports.out;

import dev.com.domain.entity.CatRolesDomain;

import java.util.List;

public interface CatRolOutPort {

    List<CatRolesDomain> listarRoles();
}
