package com.apuntes.apuntes.repository;
import com.apuntes.apuntes.model.Apunte;
import com.apuntes.apuntes.model.Archivo_Usuario;
import com.apuntes.apuntes.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

//public interface ApunteRepository extends CrudRepository<Apunte, Long> {
public interface ApunteRepository extends JpaRepository<Apunte, Long> {
    Apunte save(Apunte apunte);
    Optional<Apunte> findById(Long id);

    @NonNull
    Page<Apunte> findAll(@NonNull Pageable pageable);
    Page<Apunte> findByTitulo(String titulo,Pageable pageable);
    @Query(value = "Select * from apuntes as a inner join categorias as c " +
            "on a.id_categoria = c.id_categoria " +
            "where c.nombre like %:category%", nativeQuery=true)
    Page<Apunte> findByCategory(@Param("category") String category, Pageable pageable);
    @Query(value = "Select * from apuntes as a where a.contenido like %:contenido%", nativeQuery=true)
    Page<Apunte> findByContenido(@Param("contenido") String contenido, Pageable pageable);

}
