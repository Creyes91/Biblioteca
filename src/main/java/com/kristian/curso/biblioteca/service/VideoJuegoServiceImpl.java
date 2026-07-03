package com.kristian.curso.biblioteca.service;


import com.kristian.curso.biblioteca.model.VideoJuego;
import com.kristian.curso.biblioteca.repository.IVideoJuegoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoJuegoServiceImpl implements IVideoJuegoService {

    private IVideoJuegoRepo juegoRepo;

    public VideoJuegoServiceImpl(IVideoJuegoRepo juegoRepo) {
        this.juegoRepo = juegoRepo;
    }

    @Override
    public List<VideoJuego> findAll() {
        return juegoRepo.findAll();
    }

    @Override
    public VideoJuego findById(Long id) {
        return juegoRepo.findById(id).orElse(null);
    }


    @Override
    public void deleteById(Long id) {
        juegoRepo.deleteById(id);
    }

    @Override
    public void crear(VideoJuego videoJuego) {
        juegoRepo.save(videoJuego);
    }

}
