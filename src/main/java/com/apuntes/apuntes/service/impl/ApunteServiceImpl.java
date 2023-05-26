package com.apuntes.apuntes.service.impl;

import com.apuntes.apuntes.model.Apunte;
import com.apuntes.apuntes.model.Archivo_Usuario;
import com.apuntes.apuntes.repository.ApunteRepository;
import com.apuntes.apuntes.repository.ArchivoUsuarioRepository;
import com.apuntes.apuntes.service.ApunteService;
import com.apuntes.apuntes.service.FileServiceAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ApunteServiceImpl implements ApunteService {

    public final static Logger log = LoggerFactory.getLogger(ApunteServiceImpl.class);

    private final Path root = Paths.get("uploads");
    @Autowired
    private FileServiceAPI fileServiceAPI;
    @Autowired
    private ApunteRepository apunteRepository;
    @Autowired
    private ArchivoUsuarioRepository archivoUsuarioRepository;

    @Override
    public List<Apunte> getApuntes() {
        return (List<Apunte>) apunteRepository.findAll();
    }

    @Override
    public Page<Apunte> getApuntes(Pageable pageable) {
        return apunteRepository.findAll(pageable);
    }

    @Override
    public Page<Apunte> getApuntesByCategory(String category, Pageable pageable) {
        return apunteRepository.findByCategory(category, pageable);
    }
    @Override
    public Page<Apunte> getApuntesByTitulo(String titulo, Pageable pageable) {
        return apunteRepository.findByTitulo(titulo, pageable);
    }

    @Override
    public Page<Apunte> getApuntesByContenido(String contenido, Pageable pageable) {
        return apunteRepository.findByContenido(contenido, pageable);
    }
    @Override
    public Optional<Apunte> getApunteById(Long id) {
       return  apunteRepository.findById(id);
    }


    @Override
    public Apunte saveApunte(List<MultipartFile> images, List<MultipartFile> files, String apunte) throws Exception {
        Apunte om_apunte = new ObjectMapper().readValue(apunte, Apunte.class);
        om_apunte.setActivo(true);
        Apunte ap = apunteRepository.save(om_apunte);
        log.info(ap.toString());

        if(images.toArray().length == 0){ log.info("La lista de imagenes viene vacía"); }
        if(files.toArray().length == 0){ log.info("La lista de archivos viene vacía"); }

        List <Archivo_Usuario> arraylistImages = new ArrayList<>();
        arraylistImages = this.saveFiles("images", images, om_apunte.getId_usuario(), ap.getIdApunte(), 1);

        List <Archivo_Usuario> arraylistFiles = new ArrayList<>();
        arraylistFiles = this.saveFiles("files", files, om_apunte.getId_usuario(), ap.getIdApunte(), 2);

        List<Archivo_Usuario> resultConcat = Stream.concat(
                arraylistImages.stream(),
                arraylistFiles.stream()
        ).collect(Collectors.toList());

        ap.setArchivo_usuario(resultConcat);
        return ap;
    }

    @Override
    public Apunte updateApunte(List<MultipartFile> images, List<MultipartFile> files, String apunte) throws Exception {
        Apunte om_apunte = new ObjectMapper().readValue(apunte, Apunte.class);
        om_apunte.setActivo(true);
        Apunte ap = apunteRepository.save(om_apunte);
            
        Long idApunte = ap.getIdApunte();
        log.info("idApunte = " +  String.valueOf(idApunte));

        List<Archivo_Usuario> findByIdApunte = archivoUsuarioRepository.findByIdApunte(idApunte);
        log.info("findByIdApunte = " + findByIdApunte.toString());

        if(images.toArray().length == 0){ log.info("La lista de imagenes viene vacía"); }
        if(files.toArray().length == 0){ log.info("La lista de archivos viene vacía"); }

        List <Archivo_Usuario> arraylistImages = new ArrayList<>();
        arraylistImages = this.saveFiles("images", images, om_apunte.getId_usuario(), ap.getIdApunte(), 1);

        List <Archivo_Usuario> arraylistFiles = new ArrayList<>();
        arraylistFiles = this.saveFiles("files", files, om_apunte.getId_usuario(), ap.getIdApunte(), 2);

        List<Archivo_Usuario> resultConcat = Stream.concat(
                arraylistImages.stream(),
                arraylistFiles.stream()
        ).collect(Collectors.toList());

        List<Archivo_Usuario> result = Stream.concat(
                resultConcat.stream(),
                findByIdApunte.stream()
        ).collect(Collectors.toList());

        log.info("resultConcat = " + result.toString());
        ap.setArchivo_usuario(result);
        return ap;
    }

    List <Archivo_Usuario> saveFiles(String carpeta, List<MultipartFile> files,
                                     Long idUsuario,
                                     Long idApunte,
                                     int typeFile) throws Exception
    {
        List <Archivo_Usuario> nombredelarraylist = new ArrayList<>();
        for (MultipartFile file : files) {
            String file_name = fileServiceAPI.save(carpeta, file);
            Archivo_Usuario archivo_usuario = new Archivo_Usuario();
            archivo_usuario.setId_usuario(idUsuario);
            archivo_usuario.setNombre_archivo(file_name);
            archivo_usuario.setIdApunte(idApunte);
            archivo_usuario.setIdTypeFile(typeFile);
            Archivo_Usuario au = archivoUsuarioRepository.save(archivo_usuario);
            nombredelarraylist.add(au);
        }
        return nombredelarraylist;
    }


    /*
    @Override
    public Apunte updateApunte(List<MultipartFile> files, String apunte) throws Exception {
        Apunte om_apunte = new ObjectMapper().readValue(apunte, Apunte.class);
        om_apunte.setActivo(true);
        Apunte ap = apunteRepository.save(om_apunte);

        Long idApunte = ap.getIdApunte();
        log.info("idApunte = " +  String.valueOf(idApunte));

        List<Archivo_Usuario> findByIdApunte = archivoUsuarioRepository.findByIdApunte(idApunte);
        log.info("findByIdApunte = " + findByIdApunte.toString());

        if(files.toArray().length == 0){
            log.info("La lista de imagenes viene vacía");
        }

        List <Archivo_Usuario> nombredelarraylist = new ArrayList<>();
        for (MultipartFile file : files) {
            String file_name = fileServiceAPI.save(file);
            Archivo_Usuario archivo_usuario = new Archivo_Usuario();
            archivo_usuario.setId_usuario(om_apunte.getId_usuario());
            archivo_usuario.setNombre_archivo(file_name);
            archivo_usuario.setIdApunte(ap.getIdApunte());
            Archivo_Usuario au = archivoUsuarioRepository.save(archivo_usuario);
            nombredelarraylist.add(au);
        }
        List<Archivo_Usuario> resultConcat = Stream.concat(
                nombredelarraylist.stream(),
                findByIdApunte.stream()
        ).collect(Collectors.toList());
        log.info("resultConcat = " + resultConcat.toString());
        ap.setArchivo_usuario(resultConcat);
        return ap;
    }*/

    @Override
    public boolean deleteApunte(Long id) {
        try{
            List<Archivo_Usuario> findByIdApunte = archivoUsuarioRepository.findByIdApunte(id);
            for ( Archivo_Usuario archivo : findByIdApunte ) {
                log.info("archivo a eliminar = " + archivo.getNombre_archivo());
                this.deleteArchivo(archivo);
            }
            apunteRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteArchivo(Archivo_Usuario archivo_usuario) throws Exception {
        String typeFile = this.getTypeFile(archivo_usuario.getIdTypeFile());
        if(fileServiceAPI.deleteArchivo(typeFile+"/"+archivo_usuario.getNombre_archivo())){
            log.info("Se eliminó el archivo");
            archivoUsuarioRepository.deleteById(archivo_usuario.getId_archivo_usuario());
            return true;
        }
        return false;
    }

    public String getTypeFile(int idTypeFile){
        String typeFile = "";
        switch (idTypeFile){
            case 1:
                typeFile = "images";
                break;
            case 2:
                typeFile = "files";
                break;
            default:
                typeFile = "images";
                break;
        }
        return typeFile;
    }
}

