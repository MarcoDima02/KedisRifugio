package com.rifugio.rifugio.services;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Adozioni getAdozioneById(Integer id) {
        return adozioniRepo.findById(id).orElse(null);
    }

    @Override
    public Adozioni createAdozione(Adozioni adozione) {
        return adozioniRepo.save(adozione);
    }

    @Override
    public Adozioni updateAdozione(Integer id, Adozioni adozione) {
        Optional<Adozioni> existing = adozioniRepo.findById(id);
        if (existing.isPresent()) {
            Adozioni aggiornata = existing.get();
            aggiornata.setAnimale(adozione.getAnimale());
            aggiornata.setPersona(adozione.getPersona());
            aggiornata.setDataAdozione(adozione.getDataAdozione());
            aggiornata.setStepAdozione(adozione.getStepAdozione());
            return adozioniRepo.save(aggiornata);
        }
        return null;
    }
}
