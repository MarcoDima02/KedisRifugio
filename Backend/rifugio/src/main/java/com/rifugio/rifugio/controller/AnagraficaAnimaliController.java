package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.services.AnagraficaAnimaleServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/animali")
public class AnagraficaAnimaliController {

    @Autowired
    private AnagraficaAnimaleServiceImpl anagraficaAnimaliService;

    @GetMapping
    public List<AnagraficaAnimali> getAllAnagraficaAnimali() {
        return anagraficaAnimaliService.getAllAnagraficaAnimali();
    }

    @GetMapping("/{id}")
    public AnagraficaAnimali getByIdAnagraficaAnimali(@PathVariable int id) {
        return anagraficaAnimaliService.getByIdAnagraficaAnimali(id);
    }

    @GetMapping("/razza/{id}")
    public List<AnagraficaAnimali> getByIdRazza(@PathVariable int id) {
        return anagraficaAnimaliService.getByIdRazza(id);
    }

    @GetMapping("/speciee/{id}")
    public List<AnagraficaAnimali> getByIdSpecie(@PathVariable int id) {
        return anagraficaAnimaliService.getByIdSpecie(id);
    }

    @PostMapping("/")
    public AnagraficaAnimali create(@RequestBody AnagraficaAnimali animale) {
        return anagraficaAnimaliService.create(animale);
    }
    
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        return anagraficaAnimaliService.deleteById(id).toString();
    }

    @PutMapping("/{id}")
    public AnagraficaAnimali update(@PathVariable int id, @RequestBody AnagraficaAnimali animale) {
        return anagraficaAnimaliService.update(id, animale);
    }


}
