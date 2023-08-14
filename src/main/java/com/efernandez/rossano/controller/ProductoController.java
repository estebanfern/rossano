package com.efernandez.rossano.controller;

import com.efernandez.rossano.dao.Producto;
import com.efernandez.rossano.dto.DataTableResponse;
import com.efernandez.rossano.service.CategoriaService;
import com.efernandez.rossano.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    private final Logger logger = LoggerFactory.getLogger(ProductoController.class);

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

    @GetMapping("/save")
    public String saveNewProduct(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.findAll());
        if (error != null && !error.isEmpty()) {
            model.addAttribute("errorMsg", URLDecoder.decode(error, StandardCharsets.UTF_8));
        }
        return "productos/saveProducto";
    }

    @PostMapping("/save")
    public String saveNewProduct(Model model, Producto producto) {
        String msg = productoService.save(producto);
        if (msg.isEmpty()) {
            return "redirect:/productos?saved";
        }else {
            logger.info("User {} try to save a product {}, error={}", SecurityContextHolder.getContext().getAuthentication().getName(), producto, msg);
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaService.findAll());
            return "redirect:/productos/save?error=" + URLEncoder.encode(msg, StandardCharsets.UTF_8);
        }
    }

    @GetMapping("/search")
    @ResponseBody
    public DataTableResponse<Producto> searchProductos(@RequestParam("draw") int draw,
                                                       @RequestParam("start") int start,
                                                       @RequestParam("length") int length,
                                                       @RequestParam(name = "codeFilter", required = false) String codeFilter,
                                                       @RequestParam(name = "internalFilter", required = false) String internalFilter,
                                                       @RequestParam(name = "nameFilter", required = false) String nameFilter,
                                                       @RequestParam(name = "catFilter", required = false) String catFilter,
                                                       @RequestParam(name = "descFilter", required = false) String descFilter
    ) {
        return new DataTableResponse<>(draw, productoService.searchProductos(start, length, codeFilter, internalFilter, nameFilter, catFilter, descFilter));
    }

    @DeleteMapping("/{code}")
    @ResponseBody
    public ResponseEntity<Void> deleteProducto(@PathVariable String code) {
        productoService.delete(code);
        return ResponseEntity.ok().build();
    }

}
