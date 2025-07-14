package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Utenti;

@Service
public interface UtentiService {

    List<Utenti> getAllUtenti();
    List<Utenti> getAllUtentiAttivi(); // Solo utenti attivi
    Utenti getUtenteById(Integer id);
    Utenti getUtenteAttivoById(Integer id); // Solo se attivo
    void updateUtente(Integer id, Utenti utente);
    void deleteUtente(Integer id); // Soft delete
    void eliminaUtenteFisicamente(Integer id); // Hard delete (solo per admin)
    void ripristinaUtente(Integer id); // Ripristina utente eliminato
    Utenti createUtente(Utenti utente);
    boolean existsByEmail(String email);
    boolean existsByCodiceFiscale(String codiceFiscale);
    boolean existsActiveByEmail(String email); // Verifica solo tra attivi
    boolean existsActiveByCodiceFiscale(String codiceFiscale); // Verifica solo tra attivi
    boolean verificaPassword(String passwordInChiaro, String passwordCriptata); // Verifica password
}
