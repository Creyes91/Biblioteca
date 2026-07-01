package com.kristian.curso.biblioteca.service;

import com.kristian.curso.biblioteca.model.Plataforma;
import com.kristian.curso.biblioteca.repository.IPlataformaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlataformaService implements IPlataformaService {

    private IPlataformaRepo repo;
    public PlataformaService(IPlataformaRepo repo)
    {
           this.repo=repo;
    }

    @Override
    public void create(Plataforma plataforma) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(Plataforma plataforma) {

    }

    @Override
    public List<Plataforma> findAll() {
        return List.of();
    }




/*
    public void create(Plataforma plataforma) {
        repo.save(plataforma);
    }

    public void delete(Plataforma plataforma)
    {
        repo.delete(plataforma);
    }

    public List<Plataforma> findAll()
    {
        return repo.findAll();
    }

    public Plataforma findById(Long id)
    {
        return repo.findById(id).orElse(null);
    }
    */

}
