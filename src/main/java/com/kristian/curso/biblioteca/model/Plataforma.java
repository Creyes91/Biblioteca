package com.kristian.curso.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_plataforma")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="empresa", nullable = false)
    private String fabricante;
    @Column(nullable = false)
    private String nombre;
    private int anyo;

    public Plataforma(String fabricante, String nombre, int anyo) {
        this.fabricante = fabricante;
        this.nombre = nombre;
        this.anyo = anyo;
    }
}
