package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.AnagraficaAnimali;

@Service
public interface AnagraficaAnimaliService {

    List<AnagraficaAnimali> getAllAnagraficaAnimali();

    AnagraficaAnimali getById(int id);

    List<Anagrafica_Animali> getByIdRazza(int id);

    Anagrafica_Animali create(Anagrafica_Animali animale);

    List<Anagrafica_Animali> getByIdSpecie(int id);

    Anagrafica_Animali deleteById(int id);

}
