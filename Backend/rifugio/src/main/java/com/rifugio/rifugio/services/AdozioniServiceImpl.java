package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Adozioni;
import com.rifugio.rifugio.repos.AdozioniRepo;

@Service
public class AdozioniServiceImpl implements AdozioniService {

    @Autowired
    private AdozioniRepo adozioniRepo;

    @Override
    public List<Adozioni> getAllAdozioni() {
        return adozioniRepo.findAll();
    }
    
}
    