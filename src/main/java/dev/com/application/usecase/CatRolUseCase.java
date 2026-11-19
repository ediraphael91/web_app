package dev.com.application.usecase;

import dev.com.application.ports.in.CatRolInPort;
import dev.com.application.ports.out.CatRolOutPort;
import dev.com.domain.entity.CatRolesDomain;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CatRolUseCase implements CatRolInPort {

    private final CatRolOutPort catRolOutPort;

    public CatRolUseCase(CatRolOutPort catRolOutPort) {
        this.catRolOutPort = catRolOutPort;
    }

    @Override
    public List<CatRolesDomain> listarRoles() {
        return catRolOutPort.listarRoles();
    }
}
