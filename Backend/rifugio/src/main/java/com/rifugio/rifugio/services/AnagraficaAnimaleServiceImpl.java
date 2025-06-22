package com.rifugio.rifugio.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.repos.AnagraficaAnimaliRepo;

@Service
public class AnagraficaAnimaleServiceImpl implements AnagraficaAnimaliService {

    private final AnagraficaAnimaliRepo anagraficaAnimaliRepo;

    public AnagraficaAnimaleServiceImpl(AnagraficaAnimaliRepo anagraficaAnimaliRepo) {
    this.anagraficaAnimaliRepo = anagraficaAnimaliRepo;
    }

    @Override
    public List<AnagraficaAnimali> getAllAnagraficaAnimali() {
        return anagraficaAnimaliRepo.findAll();
    }

}
