package com.kristian.curso.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_juego")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VideoJuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String titulo;
    private int calificacion;
    private boolean favorito;
    private String urlImagen;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plataforma_id")
    private Plataforma plataforma;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id")
    private Genero genero;
}
