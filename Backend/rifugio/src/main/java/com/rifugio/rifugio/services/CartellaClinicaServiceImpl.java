package com.rifugio.rifugio.services;

import com.rifugio.rifugio.repos.CartellaClinicaRepo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Cartella_Clinica;

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

}
