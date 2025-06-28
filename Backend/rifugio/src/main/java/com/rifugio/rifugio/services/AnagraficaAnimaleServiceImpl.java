package com.rifugio.rifugio.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Anagrafica_Animali;
import com.rifugio.rifugio.repos.AnagraficaAnimaliRepo;

@Service
public class AnagraficaAnimaleServiceImpl implements AnagraficaAnimaliService {

    @Autowired
    private AnagraficaAnimaliRepo anagraficaAnimaliRepo;

    @Override
    public List<Anagrafica_Animali> getAllAnagraficaAnimali() {
        return anagraficaAnimaliRepo.findAll();
    }

    @Override
    public Anagrafica_Animali getById(int id) {
        return anagraficaAnimaliRepo.findById(id).orElse(null);
    }

    @Override
    public List<Anagrafica_Animali> getByIdRazza(int id) {
        return anagraficaAnimaliRepo.findAll().stream()
                .filter(animale -> animale.getRazza() != null && animale.getRazza().getIdRazza() == id)
                .toList();
    }

    @Override
    public List<Anagrafica_Animali> getByIdSpecie(int id) {
        return anagraficaAnimaliRepo.findAll().stream()
                .filter(animale -> animale.getSpecie() != null && animale.getSpecie().getIdSpecie() == id)
                .toList();
    }

    @Override
    public Anagrafica_Animali create(Anagrafica_Animali animale) {
        return anagraficaAnimaliRepo.save(animale);
    }

    @Override
    public Anagrafica_Animali deleteById(int id) {
        return anagraficaAnimaliRepo.findById(id)
                .map(animale -> {
                    anagraficaAnimaliRepo.delete(animale);
                    return animale;
                })
                .orElse(null);
    }

}
