package com.kristian.curso.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_plataforma")
@NoArgsConstructor
@ToString
public class Plataforma {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    @Setter
    @Column(name="empresa", nullable = false)
    private String fabricante;
    @Getter
    @Setter
    @Column(nullable = false)
    private String nombre;
    @Getter
    @Setter
    private int anyo;

    public Plataforma(String fabricante, String nombre, int anyo) {
        this.fabricante = fabricante;
        this.nombre = nombre;
        this.anyo = anyo;
    }
}
