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

import com.rifugio.rifugio.entities.Razza;
import com.rifugio.rifugio.services.RazzaService;

@RestController
@RequestMapping("/api/razze")
public class RazzeController {

    @Autowired
    private RazzaService razzaService;

    @GetMapping
    public List<Razza> getAllRazze() {
        return razzaService.getAllRazze();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Razza> getRazzaById(@PathVariable int id) {
        Razza razza = razzaService.getRazzaById(id);
        if (razza != null) {
            return ResponseEntity.ok(razza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Nuovo endpoint per ottenere razze filtrate per specie
    @GetMapping("/specie/{idSpecie}")
    public List<Razza> getRazzeBySpecie(@PathVariable Integer idSpecie) {
        return razzaService.getRazzeBySpecieId(idSpecie);
    }

    @PostMapping
    public ResponseEntity<Razza> crea(@RequestBody Razza razza) {
        return ResponseEntity.ok(razzaService.salva(razza));
    }

    @PutMapping("/{id}")            
    public ResponseEntity<Razza> aggiorna(@PathVariable int id, @RequestBody Razza nuova) {
        Razza aggiornata = razzaService.aggiorna(id, nuova);
        if (aggiornata == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(aggiornata);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable int id) {
        razzaService.elimina(id);
        return ResponseEntity.noContent().build();
    }
}
