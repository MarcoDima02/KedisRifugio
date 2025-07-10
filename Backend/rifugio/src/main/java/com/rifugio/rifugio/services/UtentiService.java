package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Utenti;

@Service
public interface UtentiService {

    List<Utenti> getAllUtenti();
    Utenti getUtenteById(Integer id);
    void updateUtente(Integer id, Utenti utente);
}
