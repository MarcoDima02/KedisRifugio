package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.services.DonazioniService;

@RestController
@RequestMapping("/donazioni")
public class DonazioniController {

    @Autowired
    private DonazioniService donazioniService;

    @GetMapping
    public List<Donazioni> getAllDonazioni() {
        return donazioniService.getAllDonazioni();
    }
}
