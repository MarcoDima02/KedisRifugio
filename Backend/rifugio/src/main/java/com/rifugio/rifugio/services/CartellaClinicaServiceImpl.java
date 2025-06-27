package com.rifugio.rifugio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Cartella_Clinica;
import com.rifugio.rifugio.repos.CartellaClinicaRepo;

@Service
public class CartellaClinicaServiceImpl implements CartellaClinicaService {

    private final CartellaClinicaRepo cartellaClinicaRepo;

    public CartellaClinicaServiceImpl(CartellaClinicaRepo cartellaClinicaRepo) {
        this.cartellaClinicaRepo = cartellaClinicaRepo;
    }

    @Override
    public List<Cartella_Clinica> getAllCartelleCliniche() {
        return cartellaClinicaRepo.findAll();
    }

    @Override
    public Optional<Cartella_Clinica> getCartellaById(int id) {
        return cartellaClinicaRepo.findById(id);
    }

    @Override
    public Cartella_Clinica salva(Cartella_Clinica cartella) {
        return cartellaClinicaRepo.save(cartella);
    }

    @Override
    public Cartella_Clinica aggiorna(int id, Cartella_Clinica nuova) {
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
    public void elimina(int id) {
        cartellaClinicaRepo.deleteById(id);
    }
}
