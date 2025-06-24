package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.StepAdozioni;
import com.rifugio.rifugio.repos.StepAdozioniRepo;

@Service
public class StepAdozioniServiceImpl implements StepAdozioniService {

    @Autowired
    private StepAdozioniRepo stepAdozioniRepo;

    @Override
    public List<StepAdozioni> getAllStepAdozioni() {
        return stepAdozioniRepo.findAll();
    }

}
