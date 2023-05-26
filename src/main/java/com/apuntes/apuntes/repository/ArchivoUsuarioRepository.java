package com.apuntes.apuntes.repository;

import com.apuntes.apuntes.model.Archivo_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArchivoUsuarioRepository extends JpaRepository<Archivo_Usuario, Long> {
    Archivo_Usuario save(Archivo_Usuario archivo_usuario);
    Optional<Archivo_Usuario> findById(Long id);
    @Query(value = "SELECT * FROM archivo_usuario WHERE id_apunte=:idApunte", nativeQuery=true)
    List<Archivo_Usuario> findByIdApunte(@Param("idApunte") Long idApunte);
    void deleteById(Long id);

}
