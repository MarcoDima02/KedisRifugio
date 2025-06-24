package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Cartella_Clinica;

@Service
public interface CartellaClinicaService {

    List<Cartella_Clinica> getAllCartelleCliniche();

}
