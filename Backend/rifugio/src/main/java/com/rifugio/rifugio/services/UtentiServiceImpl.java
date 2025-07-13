package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Utenti;
import com.rifugio.rifugio.repos.UtentiRepo;

@Service
public class UtentiServiceImpl implements UtentiService {

    @Autowired
    private UtentiRepo utentiRepo;

    @Override
    public List<Utenti> getAllUtenti() {
        return utentiRepo.findAll(); // Tutti gli utenti (inclusi eliminati) - per admin
    }

    @Override
    public List<Utenti> getAllUtentiAttivi() {
        return utentiRepo.findAllActive(); // Solo utenti attivi
    }

    @Override
    public Utenti getUtenteById(Integer id) {
        return utentiRepo.findById(id).orElse(null); // Tutti gli utenti
    }

    @Override
    public Utenti getUtenteAttivoById(Integer id) {
        return utentiRepo.findActiveById(id).orElse(null); // Solo se attivo
    }

    @Override
    public void updateUtente(Integer id, Utenti utente) {
        Utenti existing = utentiRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setNome(utente.getNome());
            existing.setCognome(utente.getCognome());
            existing.setCodiceFiscale(utente.getCodiceFiscale());
            existing.setNumero(utente.getNumero());
            existing.setEmail(utente.getEmail());
            existing.setPassword(utente.getPassword());
            existing.setRuolo(utente.getRuolo());
            // Non toccare il campo attivo nell'update normale
            utentiRepo.save(existing);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return utentiRepo.existsByEmail(email); // Tutti gli utenti
    }

    @Override
    public boolean existsByCodiceFiscale(String codiceFiscale) {
        return utentiRepo.existsByCodiceFiscale(codiceFiscale); // Tutti gli utenti
    }

    @Override
    public boolean existsActiveByEmail(String email) {
        return utentiRepo.existsActiveByEmail(email); // Solo utenti attivi
    }

    @Override
    public boolean existsActiveByCodiceFiscale(String codiceFiscale) {
        return utentiRepo.existsActiveByCodiceFiscale(codiceFiscale); // Solo utenti attivi
    }

    @Override
    public Utenti createUtente(Utenti utente) {
        // Assicurati che il nuovo utente sia attivo
        utente.setAttivo(true);
        return utentiRepo.save(utente);
    }

    @Override
    public void deleteUtente(Integer id) {
        // Soft delete - imposta attivo = false
        Utenti utente = utentiRepo.findById(id).orElse(null);
        if (utente != null) {
            utente.elimina(); // Metodo di utilità che imposta attivo = false
            utentiRepo.save(utente);
        }
    }

    @Override
    public void eliminaUtenteFisicamente(Integer id) {
        // Hard delete - solo per casi estremi (admin)
        if (utentiRepo.existsById(id)) {
            utentiRepo.deleteById(id);
        }
    }

    @Override
    public void ripristinaUtente(Integer id) {
        // Ripristina utente eliminato logicamente
        Utenti utente = utentiRepo.findById(id).orElse(null);
        if (utente != null) {
            utente.ripristina(); // Metodo di utilità che imposta attivo = true
            utentiRepo.save(utente);
        }
    }
}
