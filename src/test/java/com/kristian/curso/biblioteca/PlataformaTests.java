package com.kristian.curso.biblioteca;


import com.kristian.curso.biblioteca.model.Plataforma;
import com.kristian.curso.biblioteca.service.PlataformaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlataformaTests {

    @Autowired
    private PlataformaService plataformaService;

    @Test
    public void create()
    {
        plataformaService.create(new Plataforma("Sony","PlayStation",1994));

    }
}
