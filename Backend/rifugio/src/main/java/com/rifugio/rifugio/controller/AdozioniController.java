package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifugio.rifugio.entities.Adozioni;
import com.rifugio.rifugio.services.AdozioniService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/adozioni")
public class AdozioniController {

    @Autowired
    private AdozioniService adozioniService;

    @GetMapping
    public List<Adozioni> getAllAdozioni() {
        return adozioniService.getAllAdozioni();
    }
    
    

    



}
