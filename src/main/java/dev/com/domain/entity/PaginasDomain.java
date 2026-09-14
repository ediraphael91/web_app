package dev.com.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginasDomain<T> {
    private List<T> contenido;
    private long totalElementos;
    private int totalPaginas;
    private int paginaActual;
    private int tamanoPAgina;
}
