package com.apuntes.apuntes.service.impl;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import com.apuntes.apuntes.service.FileServiceAPI;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileServiceAPI {
    private final Path rootFolder = Paths.get("uploads");

    @Override
    public String save(String carpeta, MultipartFile file) throws Exception {
        int numero1 = (int)(Math.random()*10+1) * (int)(Math.random()*100+1) * (int)(Math.random()*10+1);
        int numero2 = (int)(Math.random()*10+1) * (int)(Math.random()*100+1) * (int)(Math.random()*10+1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy-HHmmss_S");
        String fecha_hora = dtf.format(LocalDateTime.now());
        String str = file.getOriginalFilename();
        String[] arrOfStr = str.split("\\.");
        String nombre = numero1 + "_" + fecha_hora + "_" + numero2 + "." + arrOfStr[arrOfStr.length-1];
        Files.copy(
                file.getInputStream(),
                this.rootFolder.resolve(carpeta+"/"+nombre)
        );
        return nombre;
    }

    @Override
    public Resource load(String name) throws Exception {
        Path file = rootFolder.resolve(name);
        Resource resource = new UrlResource(file.toUri());
        return resource;
    }

    @Override
    public void save(String carpeta, List<MultipartFile> files) throws Exception {
        for (MultipartFile file : files) {
            this.save(carpeta, file);
        }
    }

    @Override
    public Stream<Path> loadAll() throws Exception {
        return Files.walk(rootFolder, 1).filter(path -> !path.equals(rootFolder)).map(rootFolder::relativize);
    }
    @Override
    public boolean deleteArchivo(String filename) {
        try {
            Path file = rootFolder.resolve(filename);
            return Files.deleteIfExists(file);
        } catch (IOException e) {
           // throw new RuntimeException("Error: " + e.getMessage());
            return false;
        }
    }
}
