package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Donazioni;

@Service
public interface DonazioniService {

    List<Donazioni> getAllDonazioni();

    Donazioni getByIdDonazione(int id);

    Donazioni save(Donazioni donazione);

    Donazioni update(int id, Donazioni donazione);

    Boolean deleteById(int id);

}
