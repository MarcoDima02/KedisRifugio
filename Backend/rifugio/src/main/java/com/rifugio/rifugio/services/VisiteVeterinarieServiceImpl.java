package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.VisiteVeterinarie;
import com.rifugio.rifugio.repos.VisiteVeterinarieRepo;

@Service
public class VisiteVeterinarieServiceImpl implements VisiteVeterinarieService {

    @Autowired
    private VisiteVeterinarieRepo visiteVeterinarieRepo;

    @Override
    public List<VisiteVeterinarie> getAllVisiteVeterinarie() {
        return visiteVeterinarieRepo.findAll();
    }

    @Override
    public VisiteVeterinarie getVisitaById(Integer id) {
        return visiteVeterinarieRepo.findById(id).orElse(null);
    }

    @Override
    public VisiteVeterinarie addVisita(VisiteVeterinarie visita) {
        return visiteVeterinarieRepo.save(visita);
    }

    @Override
    public VisiteVeterinarie updateVisita(VisiteVeterinarie visita) {
        return visiteVeterinarieRepo.save(visita);
    }

    @Override
    public void deleteVisita(Integer id) {
        visiteVeterinarieRepo.deleteById(id);
    }

    



}
