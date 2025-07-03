package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.repos.DonazioneRepo;


@Service
public class DonazioniServiceImpl implements DonazioniService {

    @Autowired
    private DonazioneRepo donazioniRepo;


    @Override
    public List<Donazioni> getAllDonazioni() {
        return donazioniRepo.findAll();
    }

}
