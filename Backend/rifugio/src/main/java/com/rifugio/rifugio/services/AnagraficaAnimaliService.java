package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.AnagraficaAnimali;

@Service
public interface AnagraficaAnimaliService {

    List<AnagraficaAnimali> getAllAnagraficaAnimali();

    AnagraficaAnimali getById(int id);

}
