package com.kristian.curso.biblioteca.controller;

import com.kristian.curso.biblioteca.model.VideoJuego;
import com.kristian.curso.biblioteca.service.IGeneroService;
import com.kristian.curso.biblioteca.service.IPlataformaService;
import com.kristian.curso.biblioteca.service.VideoJuegoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/juegos")
public class VideoJuegoController {

    private IPlataformaService plataformaService;
    private IGeneroService generoService;

    private VideoJuegoServiceImpl juegoService;
    public VideoJuegoController(VideoJuegoServiceImpl juegoService, IPlataformaService plataformaService, IGeneroService generoService)
    {
        this.juegoService = juegoService;
        this.plataformaService = plataformaService;
        this.generoService = generoService;
    }


    @GetMapping("/nuevo")
    public String nuevoJuego(Model model) {
        model.addAttribute("plataformas", plataformaService.findAll());
        model.addAttribute("generos", generoService.findAll());
        return "VideoJuegosForm";
    }




}
