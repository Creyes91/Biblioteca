package com.kristian.curso.biblioteca.controller;

import com.kristian.curso.biblioteca.dto.FilaDto;
import com.kristian.curso.biblioteca.model.Genero;
import com.kristian.curso.biblioteca.model.Plataforma;
import com.kristian.curso.biblioteca.model.VideoJuego;
import com.kristian.curso.biblioteca.service.IGeneroService;
import com.kristian.curso.biblioteca.service.IPlataformaService;
import com.kristian.curso.biblioteca.service.IVideoJuegoService;
import com.kristian.curso.biblioteca.service.VideoJuegoServiceImpl;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/juegos")
public class VideoJuegoController {

    private final IPlataformaService plataformaService;
    private final IGeneroService generoService;

    private final VideoJuegoServiceImpl juegoService;
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

    @PostMapping("/guarda")
    public String guardarJuego(@ModelAttribute VideoJuego videoJuego,
                                @RequestParam("plataforma_id") Long pId,
                                @RequestParam("genero_id") Long gId)
    {
        Plataforma plataforma = plataformaService.findById(pId);
        Genero genero = generoService.findById(gId);

        videoJuego.setPlataforma(plataforma);
        videoJuego.setGenero(genero);

        juegoService.crear(videoJuego);
        return "redirect:/juegos/lista";
    }

    @GetMapping("/lista")
    public String listarJuegos(Model model)
    {
        List<VideoJuego> juegos = juegoService.findAll();

        List<FilaDto> filasJuegos = juegos.stream()
                .map(j -> new FilaDto(
                        j.getId(),
                        List.of(
                                String.valueOf(j.getId()),
                                j.getTitulo(),
                                j.getPlataforma().getNombre(),
                                j.getGenero().getNombre(),
                                j.getUrlImagen(),
                                "⭐️".repeat(j.getCalificacion()),
                                j.isFavorito() ? "❤️️":"❌"
                        )
                )).sorted(Comparator.comparing(FilaDto::getId)).toList();

        model.addAttribute("titulo", "Listado de VideoJuegos");
        model.addAttribute("h3", "Videojuegos Registrados");
        model.addAttribute("columnas", List.of("ID", "Titulo", "Plataforma", "Genero", "Imagen", "Calificacion", "Favorito"));
        model.addAttribute("filas", filasJuegos);
        model.addAttribute("rutaNueva", "/juegos/nuevo");
        model.addAttribute("rutaEditar", "/juegos/editar");
        model.addAttribute("rutaEliminar", "/juegos/eliminar");
        //para el pdf
        model.addAttribute("rutaPdf", "/juegos/pdf");
        return "ListarDatos";
    }

    @GetMapping("/editar")
    public String editarJuego(@RequestParam("id") Long id, Model model)
    {
        VideoJuego videoJuego = juegoService.findById(id);

        model.addAttribute("juego", videoJuego); // Esto es lo que falta
        model.addAttribute("titulo", "Editar Videojuego"); // Usamos este título para el h3
        model.addAttribute("plataformas", plataformaService.findAll());
        model.addAttribute("generos", generoService.findAll());
        return "VideoJuegosForm";
    }

    @GetMapping("/eliminar")
    public String eliminarJuego(@RequestParam("id") Long id)
    {
        juegoService.deleteById(id);

        return "redirect:/juegos/lista";

    }

    @GetMapping("/pdf")
    public void pdfJuego(@RequestParam Long id, HttpServletResponse response) throws IOException {
        VideoJuego videoJuego = juegoService.findById(id);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=ficha "+videoJuego.getTitulo()+".pdf");

        Document doc = new Document();
        PdfWriter.getInstance(doc, response.getOutputStream());

        doc.open();
        doc.addTitle(videoJuego.getTitulo());
        doc.add(new Paragraph("Ficha del Videojuego: " + videoJuego.getTitulo()));
        doc.add(new Paragraph("Calificación: " + videoJuego.getCalificacion()));
        doc.add(new Paragraph("Plataforma: " + videoJuego.getPlataforma().getNombre()));
        doc.add(new Paragraph("Género: " + videoJuego.getGenero().getNombre()));
        doc.add(new Paragraph("Favorito: " + (videoJuego.isFavorito() ? "Sí" : "No")));
        doc.add(new Paragraph("URL de Imagen: "+ videoJuego.getUrlImagen()));
        doc.close();
    }


}
