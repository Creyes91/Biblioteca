package com.kristian.curso.biblioteca.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_genero")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    private String descripcion;
}
