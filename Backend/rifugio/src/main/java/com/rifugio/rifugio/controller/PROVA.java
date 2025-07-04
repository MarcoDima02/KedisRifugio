package com.rifugio.rifugio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PROVA {

    @GetMapping("/prova")
    public String prova() {

        
        return "Prova funzionante!";
    }

}
