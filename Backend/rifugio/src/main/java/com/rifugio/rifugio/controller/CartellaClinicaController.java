package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifugio.rifugio.entities.Cartella_Clinica;
import com.rifugio.rifugio.services.CartellaClinicaServiceImpl;

@RestController
@RequestMapping("/cartella-clinica")
public class CartellaClinicaController {

    @Autowired
    private CartellaClinicaServiceImpl cartellaClinicaService;

    @GetMapping
    public List<Cartella_Clinica> getAllCartella_Clinicas() {
        return cartellaClinicaService.getAllCartelleCliniche();
    }

}
