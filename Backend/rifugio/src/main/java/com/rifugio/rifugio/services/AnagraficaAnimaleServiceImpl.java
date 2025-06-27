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

}
