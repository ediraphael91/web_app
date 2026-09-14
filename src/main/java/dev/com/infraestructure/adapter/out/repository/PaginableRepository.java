package dev.com.infraestructure.adapter.out.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;

public interface PaginableRepository<T> extends PanacheRepository<T> {
    default PanacheQuery<T> paginar(int page, int size) {
        return findAll().page(Page.of(page, size));
    }
    default PanacheQuery<T> paginarActivos(int page, int size) {
        return find("estado", true).page(Page.of(page, size));
    }
}
