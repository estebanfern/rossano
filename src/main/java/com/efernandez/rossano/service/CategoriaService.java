package com.efernandez.rossano.service;

import com.efernandez.rossano.dao.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> findAll();
    void save(String code, String nombre);
    void delete(String code);
}
