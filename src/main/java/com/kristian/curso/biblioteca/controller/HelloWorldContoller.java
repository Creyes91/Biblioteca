package com.kristian.curso.biblioteca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class HelloWorldContoller
    {
        @GetMapping("/hello")
        public String prueba()
        {
            return "Holaaa";
        }
    }

