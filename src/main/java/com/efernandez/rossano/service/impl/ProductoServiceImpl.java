package com.efernandez.rossano.service.impl;

import com.efernandez.rossano.config.RossanoConfig;
import com.efernandez.rossano.dao.Producto;
import com.efernandez.rossano.jdbc.ProductoJdbc;
import com.efernandez.rossano.repository.ProductoRepository;
import com.efernandez.rossano.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductoServiceImpl implements ProductoService {
    private static final Logger logger = LoggerFactory.getLogger(ProductoServiceImpl.class);
    private final ProductoRepository productoRepository;
    private final ProductoJdbc productoJdbc;
    private final RossanoConfig rossanoConfig;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoJdbc productoJdbc, RossanoConfig rossanoConfig) {
        this.productoRepository = productoRepository;
        this.productoJdbc = productoJdbc;
        this.rossanoConfig = rossanoConfig;
    }

    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    public Page<Producto> searchProductos(int start, int length, String codeFilter, String internalFilter, String nameFilter, String catFilter, String descFilter) {
        return productoJdbc.searchUsers(start, length, codeFilter, internalFilter, nameFilter, catFilter, descFilter);
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Prodcuto with id %s not found.", id))
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
