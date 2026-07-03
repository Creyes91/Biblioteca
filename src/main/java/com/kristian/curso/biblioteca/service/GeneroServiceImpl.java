package com.kristian.curso.biblioteca.service;

import com.kristian.curso.biblioteca.model.Genero;
import com.kristian.curso.biblioteca.repository.IGeneroRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl implements IGeneroService {

    private IGeneroRepo generoRepo;

    public GeneroServiceImpl(IGeneroRepo generoRepo) {
        this.generoRepo = generoRepo;
    }

    public Genero save(Genero genero) {
        return this.generoRepo.save(genero);
    }

    public List<Genero> findAll() {
        return this.generoRepo.findAll();
    }

    public Genero findById(Long id) {
        return this.generoRepo.findById(id).get();
    }

    public void delete (long id) {
        this.generoRepo.deleteById(id);
    }


}
