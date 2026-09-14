package dev.com.infraestructure.adapter;

import dev.com.application.ports.out.UsuarioOutPort;
import dev.com.domain.entity.PaginasDomain;
import dev.com.domain.entity.UsuarioDomain;
import dev.com.infraestructure.adapter.out.entity.UsuarioEntity;
import dev.com.infraestructure.adapter.out.repository.UsuarioRepository;
import dev.com.infraestructure.adapter.out.repository.mapper.UsuarioMapper;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class UsuarioAdapter implements UsuarioOutPort {

    private final UsuarioRepository repository;

    @Override
    @Transactional
    public UsuarioDomain guardarUsuario(UsuarioDomain usuario) {
        UsuarioEntity entity;
        if (usuario.getIdUsuario() != null) {
            entity = repository.findById(usuario.getIdUsuario());
            if (entity == null) {
            throw new IllegalArgumentException("Usuario no encontrrado Id:" + usuario.getIdUsuario());
        }
        entity.setNombre(usuario.getNombre());
    } else

    {
        entity = UsuarioMapper.toEntity(usuario);
        repository.persist(entity);
    }
    return UsuarioMapper.toDomain(entity);

    }

    @Override
    public List<UsuarioDomain> listarUsuarios() {
        return UsuarioMapper.toDomainList(repository.list("estado", true));
    }

    @Override
    public PaginasDomain<UsuarioDomain> listaUsuarioPag(int page, int size) {
        PanacheQuery<UsuarioEntity> query = repository.paginarActivos(page, size);

        List<UsuarioDomain> contenido = UsuarioMapper.toDomainList(query.list());
        long totalElementos = query.count();
        int totalPaginas = query.pageCount();

        return PaginasDomain.<UsuarioDomain>builder()
                .contenido(contenido)
                .totalElementos(totalElementos)
                .totalPaginas(totalPaginas)
                .paginaActual(page)
                .tamanoPAgina(size)
                .build();
    }

}


