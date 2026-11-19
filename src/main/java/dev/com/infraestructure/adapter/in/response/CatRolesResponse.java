package dev.com.infraestructure.adapter.in.response;

public record CatRolesResponse (
    Long id,
    String nombre,
    String descripcion,
    Boolean estado
) {}
