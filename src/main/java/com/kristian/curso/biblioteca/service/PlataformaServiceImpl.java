package com.kristian.curso.biblioteca.service;

import com.kristian.curso.biblioteca.model.Plataforma;
import com.kristian.curso.biblioteca.repository.IPlataformaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlataformaServiceImpl implements IPlataformaService {

    private IPlataformaRepo repo;
    public PlataformaServiceImpl(IPlataformaRepo repo) {
        this.repo=repo;
    }

    @Override
    public void create(Plataforma plataforma) {
        this.repo.save(plataforma);

    }

    @Override
    public void delete(long id) {
        this.repo.deleteById(id);

    }

    @Override
    public void update(Plataforma plataforma) {


    }

    @Override
    public List<Plataforma> findAll() {
        return List.of();
    }
}
