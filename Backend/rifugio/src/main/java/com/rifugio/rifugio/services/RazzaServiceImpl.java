package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Razza;
import com.rifugio.rifugio.repos.RazzaRepo;

@Service
public class RazzaServiceImpl implements RazzaService {

    private final RazzaRepo razzaRepo;

    public RazzaServiceImpl(RazzaRepo razzaRepo) {
    this.razzaRepo = razzaRepo;
    }

    public List<Razza> getAllRazze(){

        return razzaRepo.findAll();

    }

    

}
