package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.StatoAnimale;
import com.rifugio.rifugio.repos.StatoAnimaleRepo;

@Service
public class StatoAnimaleServiceImpl implements StatoAnimaleService{

    private final StatoAnimaleRepo statoAnimaleRepo;

    public StatoAnimaleServiceImpl(StatoAnimaleRepo statoAnimaleRepo) {
        this.statoAnimaleRepo = statoAnimaleRepo;
    }

    @Override
    public List<StatoAnimale> getAllStatiAnimali(){

        return statoAnimaleRepo.findAll();

    }

}
