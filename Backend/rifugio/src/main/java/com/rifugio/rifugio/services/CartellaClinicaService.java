package com.rifugio.rifugio.services;

import java.util.List;
import java.util.Optional;

import com.rifugio.rifugio.entities.CartellaClinica;

public interface CartellaClinicaService {
    List<CartellaClinica> getAllCartelleCliniche();
    Optional<CartellaClinica> getCartellaById(int id);
    CartellaClinica salva(CartellaClinica cartella);
    CartellaClinica aggiorna(int id, CartellaClinica nuova);
    void elimina(int id);
}
