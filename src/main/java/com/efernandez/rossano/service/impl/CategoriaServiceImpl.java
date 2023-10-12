package com.efernandez.rossano.service.impl;

import com.efernandez.rossano.dao.Categoria;
import com.efernandez.rossano.repository.CategoriaRepository;
import com.efernandez.rossano.service.CategoriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public void save(String code, String nombre) {
        Categoria categoria = new Categoria();
        categoria.setCode(code);
        categoria.setNombre(nombre);
        categoriaRepository.save(categoria);
    }

    public void delete(String code) {
        categoriaRepository.deleteById(code);
    }

}
