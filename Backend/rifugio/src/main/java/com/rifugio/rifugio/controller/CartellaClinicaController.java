package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifugio.rifugio.entities.CartellaClinica;
import com.rifugio.rifugio.services.CartellaClinicaService;

@RestController
@RequestMapping("/api/cartella-clinica")
public class CartellaClinicaController {

    @Autowired
    private CartellaClinicaService cartellaClinicaService;

    // GET - tutte le cartelle
    @GetMapping
    public List<CartellaClinica> getAll() {
        return cartellaClinicaService.getAllCartelleCliniche();
    }

    // GET - una cartella
    @GetMapping("/{id}")
    public ResponseEntity<CartellaClinica> getById(@PathVariable int id) {
        return cartellaClinicaService.getCartellaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - crea
    @PostMapping
    public ResponseEntity<CartellaClinica> create(@RequestBody CartellaClinica cartella) {
        return ResponseEntity.ok(cartellaClinicaService.salva(cartella));
    }

    // PUT - aggiorna
    @PutMapping("/{id}")
    public ResponseEntity<CartellaClinica> update(@PathVariable int id, @RequestBody CartellaClinica nuova) {
        CartellaClinica aggiornata = cartellaClinicaService.aggiorna(id, nuova);
        if (aggiornata == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aggiornata);
    }

    // DELETE - elimina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        cartellaClinicaService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
