package com.efernandez.rossano.controller;

import com.efernandez.rossano.service.CategoriaService;
import com.efernandez.rossano.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @Autowired
    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String productosView(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "productos/productos";
    }

}
