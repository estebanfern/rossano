package com.efernandez.rossano.controller.view;

import com.efernandez.rossano.config.RossanoConfig;
import com.efernandez.rossano.dao.Producto;
import com.efernandez.rossano.service.CategoriaService;
import com.efernandez.rossano.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final RossanoConfig rossanoConfig;

    private final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    public ProductoController(ProductoService productoService, CategoriaService categoriaService, RossanoConfig rossanoConfig) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.rossanoConfig = rossanoConfig;
    }

    @GetMapping
    public String productosView(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "productos/productos";
    }

    @GetMapping("/save")
    public String saveNewProduct(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("ivas", rossanoConfig.getIva());
        return "productos/saveProducto";
    }

    @GetMapping("/save/{code}")
    public String editProductView(Model model,@PathVariable Long id, @RequestParam(required = false) String error) {
        model.addAttribute("producto", productoService.findById(id));
        model.addAttribute("categorias", categoriaService.findAll());
        if (error != null && !error.isEmpty()) {
            model.addAttribute("errorMsg", URLDecoder.decode(error, StandardCharsets.UTF_8));
        }
        return "productos/saveProducto";
    }

}
