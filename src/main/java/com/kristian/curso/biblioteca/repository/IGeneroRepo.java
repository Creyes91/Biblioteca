package com.kristian.curso.biblioteca.repository;

import com.kristian.curso.biblioteca.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeneroRepo extends JpaRepository<Genero, Long> {
}
