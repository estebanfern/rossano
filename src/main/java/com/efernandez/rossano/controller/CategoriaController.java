package com.efernandez.rossano.controller;

import com.efernandez.rossano.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String categoriasView(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "categorias/categorias";
    }

    @PostMapping("/save")
    public String saveCategoria(@RequestParam String code, @RequestParam String nombre) {
        categoriaService.save(code, nombre);
        return "redirect:/categorias";
    }

    @GetMapping("/delete/{code}")
    public String saveCategoria(@PathVariable(name = "code") String code) {
        categoriaService.delete(code);
        return "redirect:/categorias";
    }


}
