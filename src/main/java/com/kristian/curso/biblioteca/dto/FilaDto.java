package com.kristian.curso.biblioteca.dto;

import java.util.List;

public class FilaDto {
    private Long id;
    private List<String> celdas;

    public FilaDto(Long id, List<String> celdas) {
        this.id = id;
        this.celdas = celdas;
    }

    public Long getId() { return id; }
    public List<String> getCeldas() { return celdas; }
}