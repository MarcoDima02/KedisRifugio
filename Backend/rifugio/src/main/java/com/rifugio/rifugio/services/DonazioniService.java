package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.entities.Utenti;

@Service
public interface DonazioniService {

    List<Donazioni> getAllDonazioni();

    List<Donazioni> getByRange(double min, double max);

    List<Donazioni> getByIdUtente(Utenti id);

    Donazioni getByIdDonazione(int id);

    Donazioni create(Donazioni donazione);

    Donazioni deleteById(int id);

    Donazioni update(int id, Donazioni donazione);

}
