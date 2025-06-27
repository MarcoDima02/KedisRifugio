package com.rifugio.rifugio.services;

import java.util.List;

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

}
