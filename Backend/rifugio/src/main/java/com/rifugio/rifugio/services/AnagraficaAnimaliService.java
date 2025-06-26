package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Anagrafica_Animali;

@Service
public interface AnagraficaAnimaliService {

    List<Anagrafica_Animali> getAllAnagraficaAnimali();

    Anagrafica_Animali getById(int id);

}
