package com.rifugio.rifugio.services;

import java.util.List;
import java.util.Optional;

import com.rifugio.rifugio.entities.Cartella_Clinica;

public interface CartellaClinicaService {
    List<Cartella_Clinica> getAllCartelleCliniche();
    Optional<Cartella_Clinica> getCartellaById(int id);
    Cartella_Clinica salva(Cartella_Clinica cartella);
    Cartella_Clinica aggiorna(int id, Cartella_Clinica nuova);
    void elimina(int id);
}
