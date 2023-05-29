package com.apuntes.apuntes.controllers;

import com.apuntes.apuntes.exception.*;
import com.apuntes.apuntes.model.Apunte;
import com.apuntes.apuntes.model.ApuntesTodos;
import com.apuntes.apuntes.model.Archivo_Usuario;
import com.apuntes.apuntes.service.ApunteService;
import com.apuntes.apuntes.service.FileServiceAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="apuntes")
public class ApunteController {
    public final static Logger log = LoggerFactory.getLogger(ApunteController.class);

    @Autowired
    private FileServiceAPI fileServiceAPI;
    @Autowired
    private ApunteService apunteService;

    @GetMapping(value = "get-apuntes")
    public Page<Apunte> getApuntes(Pageable pageable)
    {
        return apunteService.getApuntes(pageable);
    }

/*
    @PostMapping(value="get-busqueda", produces = "application/json; charset=utf-8")
    public Page<Apunte> busqueda(
            @RequestBody ApuntesTodos apuntesTodos
    ) throws Exception {
        String[] parts = apuntesTodos.getSortBy().split(",");
        String sortBy = parts[0];
        String sortDir = parts[1];

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(apuntesTodos.getPage(), apuntesTodos.getSize(), sort);
        Page<Apunte> apuntes;
        switch (apuntesTodos.getBuscarPor()){
            case "categoria":
                apuntes =  apunteService.getApuntesByCategory(apuntesTodos.getBuscar(), pageable);
                break;
            case "titulo":
                apuntes = apunteService.getApuntesByTitulo(apuntesTodos.getBuscar(), pageable);
                break;
            case "contenido":
                apuntes = apunteService.getApuntesByContenido(apuntesTodos.getBuscar(), pageable);
                break;
            default:
                apuntes = apunteService.getApuntes(pageable);
                break;
        }
        return apuntes;
    }
    */

    @PostMapping(value="get-busqueda", produces = "application/json; charset=utf-8")
    public Page<Apunte> busqueda(
            @RequestBody ApuntesTodos apuntesTodos
    ) throws Exception {
      return apunteService.busqueda(apuntesTodos);
    }

    @GetMapping(value = "get-apunte-by-id/{id}")
    public Optional<Apunte> getApunteById(@PathVariable("id") Long id) {
        return apunteService.getApunteById(id);
    }

    @PostMapping(value="save-apunte", produces = "application/json; charset=utf-8")
    public Apunte saveApunte(
            @RequestParam("images") List<MultipartFile> images,
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("apunte") String apunte
    ) throws Exception {
        return apunteService.saveApunte(images, files, apunte);
    }

    @PutMapping(value="update-apunte", produces = "application/json; charset=utf-8")
    public Apunte updateApunte(
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @RequestParam(value = "files", required = false) List<MultipartFile> files,
            @RequestParam("apunte") String apunte
    ) throws Exception {
        images = (images == null)?new ArrayList():images;
        files = (files == null)?new ArrayList():files;
        return apunteService.updateApunte(images, files, apunte);
    }

    @DeleteMapping(value="delete-apunte/{id}", produces = "application/json; charset=utf-8")
    public String deleteApunte(@PathVariable("id") Long id) {
        boolean response = apunteService.deleteApunte(id);
        if (response) {
            return "Se eliminó el apunte con id "+ id;
        }else{
            return "No se pudo eliminar el apunte "+ id;
        }
    }

    @PostMapping(value="delete-archivo", produces = "application/json; charset=utf-8")
    public boolean deleteArchivo(@RequestBody Archivo_Usuario archivo_usuario) throws Exception {
        boolean response = apunteService.deleteArchivo(archivo_usuario);
        return response;
    }

    @RequestMapping(path = "/download-file", method = RequestMethod.POST)
    public ResponseEntity<Resource> download(@RequestBody String body) throws IOException, HandleException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(body);
        String filename = jsonNode.get("filename").asText();
        String carpeta = jsonNode.get("carpeta").asText();

        // Crear un objeto Resource que represente el archivo a descargar
        Resource file = new FileSystemResource("uploads/" + carpeta + "/" + filename);

        if (!file.exists()) {
            throw new HandleException("El archivo no existe.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

        // Crear un objeto ResponseEntity que incluya el archivo y los encabezados HTTP necesarios
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .body(file);
    }

}
