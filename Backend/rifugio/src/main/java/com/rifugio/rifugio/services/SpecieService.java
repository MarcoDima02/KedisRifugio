package com.rifugio.rifugio.services;

import java.util.List;
import java.util.Optional;

import com.rifugio.rifugio.entities.Specie;

public interface SpecieService {
    List<Specie> getAllSpecie();
    Optional<Specie> getSpecieById(int id);
    Specie salva(Specie specie);
    Specie aggiorna(int id, Specie specie);
    void elimina(int id);
}
