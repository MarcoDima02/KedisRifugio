package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Utenti;
import com.rifugio.rifugio.repos.UtentiRepo;

@Service
public class UtentiServiceImpl implements UtentiService {

    @Autowired
    private final UtentiRepo utentiRepo;

    

    @Override
    public List<Utenti> getAllUtenti() {
        return utentiRepo.findAll();
    }


}
