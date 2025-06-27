package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifugio.rifugio.entities.Razza;
import com.rifugio.rifugio.services.RazzaService;

@RestController
@RequestMapping("/razze")
public class RazzeController {

    @Autowired
    private RazzaService razzeService;

    @GetMapping 
    public List<Razza> getAllRazze() {
        return razzeService.getAllRazze();
    }

}
