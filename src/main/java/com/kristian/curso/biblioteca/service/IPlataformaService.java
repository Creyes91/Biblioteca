package com.kristian.curso.biblioteca.service;

import com.kristian.curso.biblioteca.model.Plataforma;

import java.util.List;

public interface IPlataformaService
{
    public void create(Plataforma plataforma);
    public void delete(long id);
    public void update(Plataforma plataforma);
    public List<Plataforma> findAll();
}
