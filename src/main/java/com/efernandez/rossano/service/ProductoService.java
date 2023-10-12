package com.efernandez.rossano.service;

import com.efernandez.rossano.dao.Producto;
import org.springframework.data.domain.Page;

public interface ProductoService {
    String save(Producto producto);
    Page<Producto> searchProductos(int start, int length, String codeFilter, String internalFilter, String nameFilter, String catFilter, String descFilter);
    void delete(String code);
    Producto findById(String code);
    String edit(Producto producto);
}
