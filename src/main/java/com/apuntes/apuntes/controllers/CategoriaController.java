package com.apuntes.apuntes.controllers;

import com.apuntes.apuntes.dto.MsjDelete;
import com.apuntes.apuntes.model.Categoria;
import com.apuntes.apuntes.service.CategoriaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value="categorias")
public class CategoriaController {
    public final static Logger log = LoggerFactory.getLogger(CategoriaController.class);

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "get-categorias-list", produces = "application/json; charset=utf-8")
    public List<Categoria> getCategorias() {
        return categoriaService.getCategorias();
    }

    @GetMapping(value = "get-categorias", produces = "application/json; charset=utf-8")
    public Page<Categoria> getCategorias(Pageable pageable) {
        return categoriaService.getCategorias(pageable);
    }

    @GetMapping(value = "get-categoria-by-id/{id}", produces = "application/json; charset=utf-8")
    public Optional<Categoria> findByIdCategoria(@PathVariable("id") Long id) {
        log.info(String.valueOf(id));
        return  categoriaService.findById(id);
    }

    @PostMapping(value = "guardar-categoria", produces = "application/json; charset=utf-8")
    public Categoria saveCategoria(@RequestBody Categoria categoria) {
        log.info(categoria.toString());
        return categoriaService.saveCategoria(categoria);
    }

    @PutMapping(value = "update-categoria", produces = "application/json; charset=utf-8")
    private Categoria updateCategoria(@RequestBody Categoria categoria)
    {
        return categoriaService.saveCategoria(categoria);
    }

    @DeleteMapping(value = "delete-categoria-by-id/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> deleteCategoria(@PathVariable("id") Long id) throws JsonProcessingException {
        boolean response = categoriaService.deleteCategoria(id);
        MsjDelete msjDelete = new MsjDelete();
        ObjectMapper mapper = new ObjectMapper();
        if (response) {
            msjDelete.setEliminado(true);
            msjDelete.setMensaje(
                    "Se eliminó la categoría con id "+ id
            );
        }else{
            msjDelete.setEliminado(false);
            msjDelete.setMensaje(
                    "No se pudo eliminar la categoría "+ id
            );
        }
        String json = mapper.writeValueAsString(msjDelete);
        return new ResponseEntity <String>(json, HttpStatus.OK);
    }
}