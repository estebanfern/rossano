package com.efernandez.rossano.service;

import com.efernandez.rossano.dao.Producto;
import com.efernandez.rossano.jdbc.ProductoJdbc;
import com.efernandez.rossano.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoJdbc productoJdbc;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, ProductoJdbc productoJdbc) {
        this.productoRepository = productoRepository;
        this.productoJdbc = productoJdbc;
    }

    public String save(Producto producto) {
        if (productoRepository.findById(producto.getCodigoBarra()).isPresent()) {
            return String.format("Producto con Código de Barra %s ya existe.", producto.getCodigoBarra());
        }else if (productoRepository.findByCodigoInterno(producto.getCodigoInterno()).isPresent()) {
            return String.format("Producto con Código de Interno %s ya existe.", producto.getCodigoBarra());
        }else if (producto.getCat() == null || producto.getCat().isEmpty()) {
            return String.format("Debes de seleccionar una Categoria.", producto.getCodigoBarra());
        }
        productoRepository.save(producto);
        return "";
    }

    public Page<Producto> searchProductos(int start, int length, String codeFilter, String internalFilter, String nameFilter, String catFilter, String descFilter) {
        return productoJdbc.searchUsers(start, length, codeFilter, internalFilter, nameFilter, catFilter, descFilter);
    }

    public void delete(String code) {
        productoRepository.deleteById(code);
    }

    public Producto findById(String code) {
        return productoRepository.findById(code).orElseThrow(
                () -> new IllegalArgumentException(String.format("Prodcuto with codigo de barra %s not found.", code))
                );
    }

    public String edit(Producto producto) {
        Optional<Producto> other = productoRepository.findByCodigoInterno(producto.getCodigoInterno());
        if (other.isPresent() && !other.get().getCodigoBarra().equals(producto.getCodigoBarra())) {
            return String.format("Producto con Código de Interno %s ya existe.", producto.getCodigoBarra());
        }else if (producto.getCat() == null || producto.getCat().isEmpty()) {
            return String.format("Debes de seleccionar una Categoria.", producto.getCodigoBarra());
        }
        productoRepository.save(producto);
        return "";
    }
}
