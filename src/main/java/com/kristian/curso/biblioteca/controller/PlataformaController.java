package com.kristian.curso.biblioteca.controller;

import com.kristian.curso.biblioteca.model.Plataforma;
import com.kristian.curso.biblioteca.service.PlataformaServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class PlataformaController {


    private PlataformaServiceImpl plataformaService;

    public PlataformaController(PlataformaServiceImpl plataformaService)
    {
        this.plataformaService = plataformaService;
    }
    @GetMapping("/plataforma/nueva")
    public String nuevaPlataforma(Model model)
    {
        model.addAttribute("titulo","Añadir plataforma");
        model.addAttribute("nuevaPlataforma", new Plataforma());
        return "CreaPlataforma";
    }

    @GetMapping("/plataforma/lista")
    public String listarPlataformas(Model model)
    {
        List< Plataforma> plataformaList=this.plataformaService.findAll();
        model.addAttribute("plataformas", plataformaList);
        return "Plataformas";
    }

    @PostMapping("/plataforma/guardar")
    public String guardarPlatforma(@ModelAttribute("nuevaPlataforma") Plataforma plataforma)
    {
        plataformaService.create(plataforma);
        return "redirect:/plataforma/lista";
    }

    @GetMapping("/plataforma/editar")
    public String editarPlataforma(Model model, @RequestParam("id") Long id)
    {
        Plataforma p = this.plataformaService.findById(id);

        model.addAttribute("titulo","Editar plataforma");
        model.addAttribute("nuevaPlataforma", p);

        return "CreaPlataforma";
    }

    @GetMapping("/plataforma/eliminar")
    public String eliminarPlataforma(Model model, @RequestParam("id") Long id)
    {
        plataformaService.delete(id);
        return "redirect:/plataforma/lista";
    }
}
