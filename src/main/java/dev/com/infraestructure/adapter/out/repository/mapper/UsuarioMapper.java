    package dev.com.infraestructure.adapter.out.repository.mapper;

    import dev.com.domain.entity.UsuarioDomain;
    import dev.com.domain.request.UsuarioRequest;
    import dev.com.domain.response.UsuarioResponse;
    import dev.com.infraestructure.adapter.out.entity.UsuarioEntity;
    import dev.com.infraestructure.adapter.out.entity.UsuarioRolEntity;

    import java.util.List;
    import java.util.stream.Collectors;

    public class UsuarioMapper {

        private UsuarioMapper() { throw new UnsupportedOperationException("Clase utilitaria"); }

        /**
         * Convierte un UsuarioEntity a UsuarioDomain (sin rol).
         */
        public static UsuarioDomain toDomain(UsuarioEntity entity) {
            if (entity == null) return null;
            return UsuarioDomain.builder()
                    .idUsuario(entity.getIdUsuario())
                    .nombre(entity.getNombre())
                    .apellido(entity.getApellido())
                    .profesion(entity.getProfesion())
                    .correo(entity.getCorreo())
                    .contacto(entity.getContacto())
                    .fechaCumpleano(entity.getFechaCumpleano())
                    .clave(entity.getClave())
                    .estado(entity.getEstado())
                    .fechaEliminacion(entity.getFechaEliminacion())
                    .fechaModificacion(entity.getFechaModificacion())
                    .build();
        }

        /**
         * Convierte un UsuarioEntity + su UsuarioRolEntity a UsuarioDomain, incluyendo el rol.
         */
        public static UsuarioDomain toDomain(UsuarioEntity entity, UsuarioRolEntity usuarioRol) {
            UsuarioDomain domain = toDomain(entity);
            if (domain == null) return null;
            if (usuarioRol != null && usuarioRol.getRol() != null) {
                domain.setIdRol(usuarioRol.getRol().getIdRol());
                domain.setRolNombre(usuarioRol.getRol().getNombre());
            }
            return domain;
        }

        /**
         * Convierte un UsuarioDomain a UsuarioEntity (el rol se maneja aparte, en la tabla usuario_rol).
         */
        public static UsuarioEntity toEntity(UsuarioDomain domain) {
            if (domain == null) return null;

            UsuarioEntity entity = new UsuarioEntity();
            entity.setIdUsuario(domain.getIdUsuario());
            entity.setNombre(domain.getNombre());
            entity.setApellido(domain.getApellido());
            entity.setProfesion(domain.getProfesion());
            entity.setCorreo(domain.getCorreo());
            entity.setContacto(domain.getContacto());
            entity.setFechaCumpleano(domain.getFechaCumpleano());
            entity.setClave(domain.getClave());
            entity.setEstado(domain.getEstado());
            entity.setFechaEliminacion(domain.getFechaEliminacion());
            entity.setFechaModificacion(domain.getFechaModificacion());
            entity.setFechaCreacion(domain.getFechaCreacion());
            return entity;
        }

        /**
         * Convierte un UsuarioRequest a UsuarioDomain.
         */
        public static UsuarioDomain toDomain(UsuarioRequest request) {
            if (request == null) return null;
            return UsuarioDomain.builder()
                    .idUsuario(null) // se asigna en el caso de uso
                    .nombre(request.getNombre())
                    .apellido(request.getApellido())
                    .profesion(request.getProfesion())
                    .correo(request.getCorreo())
                    .contacto(request.getContacto())
                    .fechaCumpleano(request.getFechaCumpleano())
                    .clave(request.getClave())
                    .estado(request.getEstado())
                    .fechaEliminacion(request.getFechaEliminacion())
                    .fechaModificacion(null) // se calcula en el Adapter, no viene del cliente
                    .idRol(request.getIdRol())
                    .build();
        }

        /**
         * Convierte un UsuarioDomain a UsuarioResponse.
         */
        public static UsuarioResponse toResponse(UsuarioDomain domain) {
            if (domain == null) return null;
            return UsuarioResponse.builder()
                    .idUsuario(domain.getIdUsuario())
                    .nombre(domain.getNombre())
                    .apellido(domain.getApellido())
                    .profesion(domain.getProfesion())
                    .correo(domain.getCorreo())
                    .contacto(domain.getContacto())
                    .fechaCumpleano(domain.getFechaCumpleano())
                    .clave(domain.getClave())
                    .estado(domain.getEstado())
                    .fechaEliminacion(domain.getFechaEliminacion())
                    .fechaModificacion(domain.getFechaModificacion())
                    .idRol(domain.getIdRol())
                    .rol(domain.getRolNombre())
                    .build();
        }

        public static List<UsuarioDomain> toDomainList(List<UsuarioEntity> entities) {
            return entities.stream()
                    .map(UsuarioMapper::toDomain)
                    .collect(Collectors.toList());
        }

        public static List<UsuarioResponse> toResponseList(List<UsuarioDomain> domains) {
            return domains.stream()
                    .map(UsuarioMapper::toResponse)
                    .collect(Collectors.toList());
        }
    }