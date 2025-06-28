package com.rifugio.rifugio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.CartellaClinica;
import com.rifugio.rifugio.repos.CartellaClinicaRepo;

@Service
public class CartellaClinicaServiceImpl implements CartellaClinicaService {

    private final CartellaClinicaRepo cartellaClinicaRepo;

    public CartellaClinicaServiceImpl(CartellaClinicaRepo cartellaClinicaRepo) {
        this.cartellaClinicaRepo = cartellaClinicaRepo;
    }

    @Override
    public List<CartellaClinica> getAllCartelleCliniche() {
        return cartellaClinicaRepo.findAll();
    }

    @Override
    public Optional<CartellaClinica> getCartellaById(int id) {
        return cartellaClinicaRepo.findById(id);
    }

    @Override
    public CartellaClinica create(CartellaClinica cartella) {
        return cartellaClinicaRepo.save(cartella);
    }

    @Override
    public CartellaClinica update(int id, CartellaClinica nuova) {
        return cartellaClinicaRepo.findById(id).map(cartella -> {
            cartella.setSterilizzato(nuova.isSterilizzato());
            cartella.setVaccinazioni(nuova.getVaccinazioni());
            cartella.setMicrochip(nuova.getMicrochip());
            cartella.setSverminazione(nuova.isSverminazione());
            cartella.setTrattamentoAntiparassitario(nuova.isTrattamentoAntiparassitario());
            return cartellaClinicaRepo.save(cartella);
        }).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        cartellaClinicaRepo.deleteById(id);
    }
}
