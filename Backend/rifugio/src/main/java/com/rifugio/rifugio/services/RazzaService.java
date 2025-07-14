package com.rifugio.rifugio.services;

import java.util.List;

import com.rifugio.rifugio.entities.Razza;
import com.rifugio.rifugio.entities.Specie;

public interface RazzaService {
    List<Razza> getAllRazze();
    Razza getRazzaById(int id);
    Razza salva(Razza razza);
    Razza aggiorna(int id, Razza razza);
    void elimina(int id);
    List<Razza> getRazzeBySpecie(Specie specie);
    List<Razza> getRazzeBySpecieId(Integer idSpecie);
}
