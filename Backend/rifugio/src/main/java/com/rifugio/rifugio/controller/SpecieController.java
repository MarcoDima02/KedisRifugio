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

import com.rifugio.rifugio.entities.Specie;
import com.rifugio.rifugio.services.SpecieService;

@RestController
@RequestMapping("/specie")
public class SpecieController {

    @Autowired
    private SpecieService specieService;

    // GET: tutte le specie
    @GetMapping
    public List<Specie> getAllSpecie() {
        return specieService.getAllSpecie();
    }

    // GET: una specie per ID
    @GetMapping("/{id}")
    public ResponseEntity<Specie> getSpecieById(@PathVariable int id) {
        return specieService.getSpecieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: crea nuova specie
    @PostMapping
    public ResponseEntity<Specie> createSpecie(@RequestBody Specie specie) {
        return ResponseEntity.ok(specieService.salva(specie));
    }

    // PUT: aggiorna specie
    @PutMapping("/{id}")
    public ResponseEntity<Specie> updateSpecie(@PathVariable int id, @RequestBody Specie nuova) {
        Specie aggiornata = specieService.aggiorna(id, nuova);
        if (aggiornata == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(aggiornata);
    }

    // DELETE: elimina specie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecie(@PathVariable int id) {
        specieService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
