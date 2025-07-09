package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rifugio.rifugio.entities.VisiteVeterinarie;
import com.rifugio.rifugio.services.VisiteVeterinarieService;

@RestController
@RequestMapping("/api/visite-veterinarie")
public class VisiteVeterinarieController {

    @Autowired
    private VisiteVeterinarieService visiteVeterinarieService;

    @GetMapping
    public List<VisiteVeterinarie> getAllVisiteVeterinarie() {
        return visiteVeterinarieService.getAllVisiteVeterinarie();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisiteVeterinarie> getVisitaById(@PathVariable Integer id) {
        VisiteVeterinarie visita = visiteVeterinarieService.getVisitaById(id);
        if (visita != null) {
            return ResponseEntity.ok(visita);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public VisiteVeterinarie createVisita(@RequestBody VisiteVeterinarie nuovaVisita) {
        return visiteVeterinarieService.addVisita(nuovaVisita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisiteVeterinarie> updateVisita(@PathVariable Integer id, @RequestBody VisiteVeterinarie visitaAggiornata) {
        visitaAggiornata.setId_visita(id);
        VisiteVeterinarie aggiornata = visiteVeterinarieService.updateVisita(visitaAggiornata);
        if (aggiornata != null) {
            return ResponseEntity.ok(aggiornata);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisita(@PathVariable Integer id) {
        visiteVeterinarieService.deleteVisita(id);
        return ResponseEntity.noContent().build();
    }
}
