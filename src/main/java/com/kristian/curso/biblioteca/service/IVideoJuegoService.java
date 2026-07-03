package com.kristian.curso.biblioteca.service;

import com.kristian.curso.biblioteca.model.VideoJuego;

import java.util.List;

public interface IVideoJuegoService {

    public List<VideoJuego> findAll();
    public VideoJuego findById(Long id);
    public void deleteById(Long id);
    public void crear(VideoJuego videoJuego);
}
