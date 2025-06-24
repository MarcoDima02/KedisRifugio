package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.StepAdozioni;

@Service
public interface StepAdozioniService {

    List<StepAdozioni> getAllStepAdozioni();
}
