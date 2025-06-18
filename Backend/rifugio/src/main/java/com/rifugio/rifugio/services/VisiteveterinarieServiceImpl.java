package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rifugio.rifugio.entities.Visiteveterinarie;
import com.rifugio.rifugio.repos.VisiteveterinarieRepo;

public class VisiteveterinarieServiceImpl implements VisiteveterinarieService {
    @Autowired
    private VisiteveterinarieRepo repo;

    @Override
    public List<Visiteveterinarie> getVisiteVeterinarie() {
        return repo.findAll();
    }

}
