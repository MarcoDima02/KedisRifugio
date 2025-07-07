package com.rifugio.rifugio.controller;

import com.rifugio.rifugio.entities.Razza;
import com.rifugio.rifugio.services.RazzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return razzaService.getRazzaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
