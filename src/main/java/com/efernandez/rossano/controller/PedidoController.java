package com.efernandez.rossano.controller;

import com.efernandez.rossano.constants.PedidoStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @GetMapping
    public String pedidos(Model model) {
        model.addAttribute("estados", PedidoStatus.values());
        return "pedidos/pedidos";
    }

}
