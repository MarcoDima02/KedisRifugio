package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifugio.rifugio.entities.Anagrafica_Animali;
import com.rifugio.rifugio.services.AnagraficaAnimaleServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/animali")
public class AnagraficaAnimaliController {

    @Autowired
    private AnagraficaAnimaleServiceImpl anagraficaAnimaliService;

    @GetMapping
    public List<Anagrafica_Animali> getAllAnagraficaAnimali() {
        return anagraficaAnimaliService.getAllAnagraficaAnimali();
    }

    @GetMapping("/razza/{id}")
    public List<Anagrafica_Animali> getByIdRazza(@PathVariable int id) {
        return anagraficaAnimaliService.getByIdRazza(id);
    }

    @GetMapping("/specie/{id}")
    public List<Anagrafica_Animali> getByIdSpecie(@PathVariable int id) {
        return anagraficaAnimaliService.getByIdSpecie(id);
    }

    @PostMapping("/add")
    public String creaAnimale(@RequestBody Anagrafica_Animali animale) {

        return anagraficaAnimaliService.create(animale).toString();
    }
    
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        return anagraficaAnimaliService.deleteById(id).toString();
    }


}
