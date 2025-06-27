package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.services.AnagraficaAnimaleServiceImpl;

@RestController
@RequestMapping("/animali")
public class AnagraficaAnimaliController {

    @Autowired
    private AnagraficaAnimaleServiceImpl anagraficaAnimaliService;

    @GetMapping
    public List<AnagraficaAnimali> getAllAnagraficaAnimali() {
        return anagraficaAnimaliService.getAllAnagraficaAnimali();
    }



}
