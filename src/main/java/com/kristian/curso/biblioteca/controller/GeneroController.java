package com.kristian.curso.biblioteca.controller;


import com.kristian.curso.biblioteca.dto.FilaDto;
import com.kristian.curso.biblioteca.dto.FormDto;
import com.kristian.curso.biblioteca.model.Genero;
import com.kristian.curso.biblioteca.service.GeneroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GeneroController {

    private GeneroServiceImpl generoService;

    @Autowired
    public GeneroController(GeneroServiceImpl generoService) {

        this.generoService = generoService;
    }

    @GetMapping("/genero/lista")
    public String listarGeneros(Model model) {
        List<Genero> generos = generoService.findAll();

        List<FilaDto> filas = generos.stream()
                .map(g -> new FilaDto(
                        g.getId(),
                        List.of(
                                String.valueOf(g.getId()),
                                g.getNombre(),
                                g.getDescripcion()
                        )
                ))
                .toList();

        model.addAttribute("titulo", "Listado de Géneros");
        model.addAttribute("h3", "Géneros Registrados");
        model.addAttribute("columnas", List.of("ID", "Nombre", "Descripcion"));
        model.addAttribute("filas", filas);
        model.addAttribute("rutaNueva", "/genero/nueva");
        model.addAttribute("rutaEditar", "/genero/editar");
        model.addAttribute("rutaEliminar", "/genero/eliminar");

        return "ListarDatos";
    }

    @GetMapping("/genero/nuevo")
    public String crearGenero(Model model) {
        model.addAttribute("titulo", "Registrar Nuevo Género");
        model.addAttribute("action", "/genero/guardar");
        model.addAttribute("id", null); // null para nuevo, o el ID para editar

        // Aquí definimos los campos específicos para Género
        model.addAttribute("campos", List.of(
                new FormDto("nombre", "Nombre del Género", "text", "Acción"),
                new FormDto("descripcion", "Descripcion", "text", "")
        ));

        return "FormularioCrear"; // O el nombre de tu archivo HTML de formulario
    }

    @PostMapping("/genero/guardar")
    public String guardarGenero(@RequestParam Map<String, String> allParams) {
        // Extraemos los datos del mapa genérico
        String nombre = allParams.get("nombre");
        String idStr = allParams.get("id");
        String descr = allParams.get("descripcion");

        Genero genero = new Genero();
        if (idStr != null && !idStr.isEmpty()) {
            genero.setId(Long.parseLong(idStr));
        }
        genero.setNombre(nombre);
        genero.setDescripcion(descr);
        generoService.save(genero);

        return "redirect:/genero/lista";
    }

    @GetMapping("/genero/editar")
    public String editarGenero(Model model, @RequestParam("id") Long id) {
        // 1. Buscamos el objeto en la base de datos
        Genero g = this.generoService.findById(id);

        // 2. Preparamos los datos genéricos que espera el HTML
        model.addAttribute("titulo", "Editar Género");
        model.addAttribute("action", "/genero/guardar");
        model.addAttribute("id", g.getId()); // Pasamos el ID para el campo hidden

        // 3. Rellenamos la lista de campos con los valores actuales
        model.addAttribute("campos", List.of(
                new FormDto("nombre", "Nombre del Género", "text", g.getNombre()),
                new FormDto("descripcion", "Descripción", "text", g.getDescripcion())
        ));

        return "FormularioCrear";
    }

    @GetMapping("/genero/eliminar")
    public String eliminarGenero(@RequestParam("id") Long id) {
        // Llamamos al servicio para eliminar el registro
        generoService.delete(id);

        // Redirigimos a la lista (recuerda usar la ruta completa si es necesario)
        return "redirect:/genero/lista";
    }

}
