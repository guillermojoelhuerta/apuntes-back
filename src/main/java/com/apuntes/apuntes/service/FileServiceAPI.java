package com.apuntes.apuntes.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceAPI {

    public String save(String carpeta, MultipartFile file) throws Exception;

    public Resource load(String name) throws Exception;

    public void save(String carpeta, List<MultipartFile> files) throws Exception;

    public Stream<Path> loadAll() throws Exception;

    public boolean deleteArchivo(String name) throws Exception;
}
