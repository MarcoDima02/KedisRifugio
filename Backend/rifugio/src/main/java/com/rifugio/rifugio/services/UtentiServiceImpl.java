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
        return utentiRepo.findAll();
    }

    @Override
    public Utenti getUtenteById(Integer id) {
        return utentiRepo.findById(id).orElse(null);
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
            utentiRepo.save(existing);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return utentiRepo.existsByEmail(email);
    }

    @Override
    public boolean existsByCodiceFiscale(String codiceFiscale) {
        return utentiRepo.existsByCodiceFiscale(codiceFiscale);
    }

    @Override
    public Utenti createUtente(Utenti utente) {
        return utentiRepo.save(utente);
    }

    @Override
    public void deleteUtente(Integer id) {
        if (utentiRepo.existsById(id)) {
            utentiRepo.deleteById(id);
        }
    }

}
