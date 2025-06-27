package com.rifugio.rifugio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Specie;
import com.rifugio.rifugio.repos.SpecieRepo;

@Service
public class SpecieServiceImpl implements SpecieService {

    private final SpecieRepo specieRepo;

    public SpecieServiceImpl(SpecieRepo specieRepo) {
        this.specieRepo = specieRepo;
    }

    @Override
    public List<Specie> getAllSpecie() {
        return specieRepo.findAll();
    }

    @Override
    public Optional<Specie> getSpecieById(int id) {
        return specieRepo.findById(id);
    }

    @Override
    public Specie salva(Specie specie) {
        return specieRepo.save(specie);
    }

    @Override
    public Specie aggiorna(int id, Specie nuova) {
        return specieRepo.findById(id).map(specie -> {
            specie.setNome(nuova.getNome());
            return specieRepo.save(specie);
        }).orElse(null);
    }

    @Override
    public void elimina(int id) {
        specieRepo.deleteById(id);
    }
}
