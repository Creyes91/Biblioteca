package com.kristian.curso.biblioteca.controller;

import com.kristian.curso.biblioteca.service.VideoJuegoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/juegos")
public class VideoJuegoController {

    private VideoJuegoServiceImpl juegoService;
    public VideoJuegoController(VideoJuegoServiceImpl juegoService)
    {
        this.juegoService = juegoService;
    }





}
