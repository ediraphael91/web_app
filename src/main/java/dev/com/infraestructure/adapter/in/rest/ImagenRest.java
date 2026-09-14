package dev.com.infraestructure.adapter.in.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.io.File;

@Path("/uploads/cursos")
public class ImagenRest {

    @GET
    @Path("{nombre}")
    @Produces({"image/png", "image/jpeg"})
    public Response getImagen(@PathParam("nombre") String nombre) {
        File file = new File("uploads/cursos", nombre);
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(file).build();
    }
}
