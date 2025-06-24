package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Adozioni;

@Service
public interface AdozioniService {

    List<Adozioni> getAllAdozioni();

}
