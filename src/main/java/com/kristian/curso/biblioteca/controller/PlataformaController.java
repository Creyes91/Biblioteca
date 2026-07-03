package com.kristian.curso.biblioteca.controller;

import com.kristian.curso.biblioteca.dto.FilaDto;
import com.kristian.curso.biblioteca.dto.FormDto;
import com.kristian.curso.biblioteca.model.Plataforma;
import com.kristian.curso.biblioteca.service.PlataformaServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Controller
public class PlataformaController {


    private PlataformaServiceImpl plataformaService;

    public PlataformaController(PlataformaServiceImpl plataformaService)
    {
        this.plataformaService = plataformaService;
    }

    @GetMapping("/plataforma/nueva")
    public String crearPlataforma(Model model) {
        model.addAttribute("titulo", "Registrar Nueva Plataforma");
        model.addAttribute("action", "/plataforma/guardar");
        model.addAttribute("id", null); // O el ID si es edición
        model.addAttribute("campos", List.of(
                new FormDto("fabricante", "Fabricante / Empresa", "text", "Sony"),
                new FormDto("nombre", "Nombre de la Plataforma", "text", "PlayStation 5"),
                new FormDto("anyo", "Año de Lanzamiento", "number", "2020")
        ));
        return "FormularioCrear"; // O el nombre de tu archivo html
    }


    @GetMapping("/plataforma/lista")
    public String listarPlataformas(Model model) {
        List<Plataforma> plataformas = this.plataformaService.findAll();


        List<FilaDto> filas = plataformas.stream()
                .map(p -> new FilaDto(p.getId(), List.of(
                        String.valueOf(p.getId()), // ERROR 1: Deben ser String
                        p.getFabricante(),
                        p.getNombre(),
                        String.valueOf(p.getAnyo()) // ERROR 1: Deben ser String
                )))
                .toList();

        model.addAttribute("titulo", "Catálogo de Plataformas");
        model.addAttribute("columnas", List.of("ID", "Fabricante","Plataforma", "Lanzamiento")); // ERROR 2: Debes pasar "columnas", no "celdas"
        model.addAttribute("filas", filas);
        model.addAttribute("rutaNueva", "/plataforma/nueva");
        model.addAttribute("rutaEditar", "/plataforma/editar");
        model.addAttribute("rutaEliminar", "/plataforma/eliminar");

        return "ListarDatos";
    }

   /* @GetMapping("/plataforma/lista")
    public String listarPlataformas(Model model)
    {

        List< Plataforma> plataformaList=this.plataformaService.findAll();
        model.addAttribute("plataformas", plataformaList);
        return "ListarDatos";
    }*/

    @PostMapping("/plataforma/guardar")
    public String guardarPlatforma(@RequestParam Map<String, String> allParams) {
        // 1. Crear o recuperar la plataforma
        Plataforma p = new Plataforma();

        // 2. Procesar el ID (si viene vacío, lo dejamos como null o 0 según tu lógica)
        String idStr = allParams.get("id");
        if (idStr != null && !idStr.isEmpty()) {
            p.setId(Long.parseLong(idStr));
        }

        // 3. Asignar el resto de valores
        p.setFabricante(allParams.get("fabricante"));
        p.setNombre(allParams.get("nombre"));

        // 4. Convertir el año (asegúrate de que llegue bien)
        String anyoStr = allParams.get("anyo");
        if (anyoStr != null && !anyoStr.isEmpty()) {
            p.setAnyo(Integer.parseInt(anyoStr));
        }

        // 5. Guardar
        plataformaService.create(p);
        return "redirect:/plataforma/lista";
    }

    @GetMapping("/plataforma/editar")
    public String editarPlataforma(Model model, @RequestParam("id") Long id) {
        Plataforma p = this.plataformaService.findById(id);

        model.addAttribute("titulo", "Editar Plataforma");
        model.addAttribute("action", "/plataforma/guardar");
        model.addAttribute("id", p.getId()); // Pasamos el ID para el input hidden

        // Rellenamos los campos con los valores del objeto recuperado
        model.addAttribute("campos", List.of(
                new FormDto("fabricante", "Fabricante / Empresa", "text", p.getFabricante()),
                new FormDto("nombre", "Nombre de la Plataforma", "text", p.getNombre()),
                new FormDto("anyo", "Año de Lanzamiento", "number", String.valueOf(p.getAnyo()))
        ));

        return "FormularioCrear";
    }

    @GetMapping("/plataforma/eliminar")
    public String eliminarPlataforma(Model model, @RequestParam("id") Long id)
    {
        plataformaService.delete(id);
        return "redirect:/plataforma/lista";
    }
}
