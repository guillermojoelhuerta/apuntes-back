package com.apuntes.apuntes.service.impl;
import com.apuntes.apuntes.model.Categoria;
import com.apuntes.apuntes.repository.CategoriaRepository;
import com.apuntes.apuntes.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public Categoria buscarTodosByNombre(String nombre) {
        return null;
    }

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> getCategorias() {
        return (List<Categoria>) categoriaRepository.findAll();
    }
    @Override
    public Page<Categoria> getCategorias(Pageable pageable) {
        //return (List<Categoria>) categoriaRepository.findAll(pageable);
        return (Page<Categoria>) categoriaRepository.findAll(pageable);
    }
    @Override
    public boolean deleteCategoria(Long id) {
        try {
            categoriaRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }
}
