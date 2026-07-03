package com.kristian.curso.biblioteca.service;

import com.kristian.curso.biblioteca.model.Genero;

import java.util.List;

public interface IGeneroService {

    public Genero save(Genero genero);
    public List<Genero> findAll();
    public Genero findById(Long id);
    public void delete (long id);

}
