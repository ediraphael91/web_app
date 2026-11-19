package dev.com.infraestructure.adapter.in.rest;

import dev.com.application.ports.in.CatRolInPort;
import dev.com.infraestructure.adapter.in.response.CatRolesResponse;
import dev.com.infraestructure.adapter.out.repository.mapper.CatRolesMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
public class CatRolRest {

private final CatRolInPort catRolInPort;
private final CatRolesMapper catRolesMapper;

    public CatRolRest(
            CatRolInPort catRolInPort,
            CatRolesMapper catRolesMapper) {
        this.catRolInPort = catRolInPort;
        this.catRolesMapper = catRolesMapper;
    }

    @GET
    public List<CatRolesResponse> listarRoles() {

        return catRolInPort.listarRoles()
                .stream()
                .map(catRolesMapper::toResponse)
                .toList();
    }

}
