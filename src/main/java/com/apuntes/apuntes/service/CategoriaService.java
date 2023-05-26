package com.apuntes.apuntes.service;
import com.apuntes.apuntes.model.Apunte;
import com.apuntes.apuntes.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    Categoria buscarTodosByNombre(String nombre);
    Categoria saveCategoria(Categoria categoria);
    List<Categoria> getCategorias();
    Page<Categoria> getCategorias(Pageable pageable);

    boolean deleteCategoria(Long id);
    Optional<Categoria> findById(Long id);
}
