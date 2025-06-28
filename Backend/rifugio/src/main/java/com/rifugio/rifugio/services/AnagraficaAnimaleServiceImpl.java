package com.rifugio.rifugio.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.repos.AnagraficaAnimaliRepo;

@Service
public class AnagraficaAnimaleServiceImpl implements AnagraficaAnimaliService {

    @Autowired
    private AnagraficaAnimaliRepo anagraficaAnimaliRepo;

    @Override
    public List<AnagraficaAnimali> getAllAnagraficaAnimali() {
        return anagraficaAnimaliRepo.findAll();
    }

    @Override
    public AnagraficaAnimali getById(int id) {
        return anagraficaAnimaliRepo.findById(id).orElse(null);
    }

    @Override
    public List<AnagraficaAnimali> getByIdRazza(int id) {
        return anagraficaAnimaliRepo.findAll().stream()
                .filter(animale -> animale.getRazza() != null && animale.getRazza().getIdRazza() == id)
                .toList();
    }

    @Override
    public List<AnagraficaAnimali> getByIdSpecie(int id) {
        return anagraficaAnimaliRepo.findAll().stream()
                .filter(animale -> animale.getSpecie() != null && animale.getSpecie().getIdSpecie() == id)
                .toList();
    }

    @Override
    public AnagraficaAnimali create(AnagraficaAnimali animale) {
        return anagraficaAnimaliRepo.save(animale);
    }

    @Override
    public AnagraficaAnimali deleteById(int id) {
        return anagraficaAnimaliRepo.findById(id)
                .map(animale -> {
                    anagraficaAnimaliRepo.delete(animale);
                    return animale;
                })
                .orElse(null);
    }

}
