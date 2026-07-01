package com.kristian.curso.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PlataformaController {

    @GetMapping("/plataformas/nueva")
    public String nuevaPlataforma(Model model)
    {
        return "CreaPlataforma";
    }

    @GetMapping("/plataforma")
    public String listarPlataformas(Model model)
    {
        return "Plataformas";
    }
}
