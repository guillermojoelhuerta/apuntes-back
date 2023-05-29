package com.apuntes.apuntes.service;

import com.apuntes.apuntes.model.Apunte;
import com.apuntes.apuntes.model.ApuntesTodos;
import com.apuntes.apuntes.model.Archivo_Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ApunteService {
    List<Apunte> getApuntes();
    Page<Apunte> getApuntes(Pageable pageable);
    Optional<Apunte> getApunteById(Long id);
    Apunte saveApunte(List<MultipartFile> images, List<MultipartFile> files, String apunte) throws Exception;
    Apunte updateApunte(List<MultipartFile> images, List<MultipartFile> files, String apunte) throws Exception;
    boolean deleteApunte(Long id);
    boolean deleteArchivo(Archivo_Usuario archivo_usuario) throws Exception;
    Page<Apunte> busqueda(ApuntesTodos apuntesTodos);
}
