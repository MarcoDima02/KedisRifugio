package com.rifugio.rifugio.services;

import java.util.List;
import java.util.Optional;

import com.rifugio.rifugio.entities.CartellaClinica;

public interface CartellaClinicaService {
    List<CartellaClinica> getAllCartelleCliniche();
    Optional<CartellaClinica> getCartellaById(int id);
    CartellaClinica create(CartellaClinica cartella);
    CartellaClinica update(int id, CartellaClinica nuova);
    void deleteById(int id);
}
