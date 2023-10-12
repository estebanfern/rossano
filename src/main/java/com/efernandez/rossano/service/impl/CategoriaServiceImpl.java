package com.efernandez.rossano.service;

import com.efernandez.rossano.dao.Categoria;
import com.efernandez.rossano.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
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
