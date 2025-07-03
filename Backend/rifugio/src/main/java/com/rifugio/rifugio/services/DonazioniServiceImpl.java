package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.repos.DonazioneRepo;


@Service
public class DonazioniServiceImpl implements DonazioniService {

    @Autowired
    private DonazioneRepo donazioniRepo;


    @Override
    public List<Donazioni> getAllDonazioni() {
        return donazioniRepo.findAll();
    }

    @Override
    public Donazioni getByIdDonazione(int id){
        return donazioniRepo.findById(id).orElse(null);
    }

    @Override
    public Donazioni update(int id, Donazioni donazione) {
        return donazioniRepo.findById(id)
                .map(existingDonazione -> {
                    existingDonazione.setImporto(donazione.getImporto());
                    existingDonazione.setDescrizione(donazione.getDescrizione());
                    return donazioniRepo.save(existingDonazione);
                })
                .orElse(null);
    }

}
