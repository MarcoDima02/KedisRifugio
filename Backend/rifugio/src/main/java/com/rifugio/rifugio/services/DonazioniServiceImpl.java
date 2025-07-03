package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.entities.Utenti;
import com.rifugio.rifugio.repos.DonazioneRepo;


@Service
public class DonazioniServiceImpl implements DonazioniService {

    @Autowired
    private DonazioneRepo donazioniRepo;


    @Override
    public List<Donazioni> getAllDonazioni() {
        return donazioniRepo.findAll();
    }

    @Override
    public List<Donazioni> getByRange(double min, double max) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByRange'");
    }

    @Override
    public List<Donazioni> getByIdUtente(Utenti id) {

        return donazioniRepo.findByIdPersona(id);
    }

    @Override
    public Donazioni getByIdDonazione(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByIdDonazione'");
    }

    @Override
    public Donazioni create(Donazioni donazione) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Donazioni deleteById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Donazioni update(int id, Donazioni donazione) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
