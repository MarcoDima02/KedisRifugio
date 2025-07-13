package com.rifugio.rifugio.services;

import java.util.List;
import java.util.Optional;
import com.rifugio.rifugio.entities.Razza;

public interface RazzaService {
    List<Razza> getAllRazze();
    Optional<Razza> getRazzaById(int id);
    Razza salva(Razza razza);
    Razza aggiorna(int id, Razza razza);
    void elimina(int id);
    
    // Nuovi metodi per filtrare per specie
    List<Razza> getRazzeBySpecieId(Integer specieId);
}
