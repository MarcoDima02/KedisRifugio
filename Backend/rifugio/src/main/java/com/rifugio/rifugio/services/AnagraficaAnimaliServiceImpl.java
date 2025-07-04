package com.rifugio.rifugio.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.repos.AnagraficaAnimaliRepo;

@Service
public class AnagraficaAnimaliServiceImpl implements AnagraficaAnimaliService {

    @Autowired
    private AnagraficaAnimaliRepo anagraficaAnimaliRepo;

    @Override
    public List<AnagraficaAnimali> getAllAnagraficaAnimali() {
        return anagraficaAnimaliRepo.findAll();
    }

    @Override
    public AnagraficaAnimali getByIdAnagraficaAnimali(int id) {
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

    @Override
    public AnagraficaAnimali update(int id, AnagraficaAnimali animale) {
        return anagraficaAnimaliRepo.findById(id)
                .map(existingAnimale -> {
                    existingAnimale.setNome(animale.getNome());
                    existingAnimale.setRazza(animale.getRazza());
                    existingAnimale.setSpecie(animale.getSpecie());
                    existingAnimale.setSesso(animale.getSesso());
                    existingAnimale.setDataNascita(animale.getDataNascita());
                    existingAnimale.setIdCartellaClinica(animale.getIdCartellaClinica());
                    return anagraficaAnimaliRepo.save(existingAnimale);
                })
                .orElse(null);
    }

    @Override
    public List<AnagraficaAnimali> getByIdStatoAnimale(int id) {
        return anagraficaAnimaliRepo.findAll().stream()
                .filter(animale -> animale.getIdStatoAnimale() != null && animale.getIdStatoAnimale().getIdStatoAnimale() == id)
                .toList();
    }

    @Override
    public List<AnagraficaAnimali> filtra(Integer specie, Integer razza, Character sesso) {
        return anagraficaAnimaliRepo.findAll().stream()
            .filter(animale -> specie == null || animale.getSpecie().getIdSpecie().equals(specie))
            .filter(animale -> razza == null || animale.getRazza().getIdRazza().equals(razza))
            .filter(animale -> sesso == null || animale.getSesso() == sesso)
            .filter(animale -> animale.getIdStatoAnimale() != null && animale.getIdStatoAnimale().getIdStatoAnimale() == 1)  // solo disponibili
            .collect(Collectors.toList());
    }



}
