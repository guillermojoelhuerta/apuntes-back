package com.apuntes.apuntes.repository;

import com.apuntes.apuntes.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria save(Categoria categoria);
    Optional<Categoria> findById(Long id);
}
