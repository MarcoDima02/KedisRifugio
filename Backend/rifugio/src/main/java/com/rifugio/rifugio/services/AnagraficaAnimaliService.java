package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Anagrafica_Animali;

@Service
public interface AnagraficaAnimaliService {

    List<Anagrafica_Animali> getAllAnagraficaAnimali();

    Anagrafica_Animali getById(int id);

    List<Anagrafica_Animali> getByIdRazza(int id);

    Anagrafica_Animali create(Anagrafica_Animali animale);

    List<Anagrafica_Animali> getByIdSpecie(int id);

    Anagrafica_Animali deleteById(int id);

}
