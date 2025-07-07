package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rifugio.rifugio.entities.Adozioni;
import com.rifugio.rifugio.services.AdozioniService;

@RestController
@RequestMapping("/api/adozioni")
public class AdozioniController {

    @Autowired
    private AdozioniService adozioniService;

    @GetMapping
    public List<Adozioni> getAllAdozioni() {
        return adozioniService.getAllAdozioni();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adozioni> getAdozioneById(@PathVariable Integer id) {
        Adozioni adozione = adozioniService.getAdozioneById(id);
        if (adozione != null) {
            return ResponseEntity.ok(adozione);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Adozioni createAdozione(@RequestBody Adozioni nuovaAdozione) {
        return adozioniService.createAdozione(nuovaAdozione);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adozioni> updateAdozione(@PathVariable Integer id, @RequestBody Adozioni adozioneAggiornata) {
        Adozioni aggiornata = adozioniService.updateAdozione(id, adozioneAggiornata);
        if (aggiornata != null) {
            return ResponseEntity.ok(aggiornata);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
