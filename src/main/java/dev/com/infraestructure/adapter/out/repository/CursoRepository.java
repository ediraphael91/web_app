package dev.com.infraestructure.adapter.out.repository;

import dev.com.application.ports.out.CursoOutPort;
import dev.com.domain.entity.CursoDomain;
import dev.com.infraestructure.adapter.out.entity.CursoEntity;
import dev.com.infraestructure.adapter.out.repository.mapper.CursoMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>
 * Curso Repository.
 * </b>
 *
 * @author Edison Santacruz
 * @version : 1.0 $
 * <p>
 * [: Edison Santacruz $, : 07/05/2026 $]
 * </p>
 */
@ApplicationScoped
public class CursoRepository implements PanacheRepository<CursoEntity> {

}
