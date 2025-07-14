package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Razza;
import com.rifugio.rifugio.entities.Specie;
import com.rifugio.rifugio.repos.RazzaRepo;

@Service
public class RazzaServiceImpl implements RazzaService {

    private final RazzaRepo razzaRepo;

    public RazzaServiceImpl(RazzaRepo razzaRepo) {
        this.razzaRepo = razzaRepo;
    }

    @Override
    public List<Razza> getAllRazze() {
        return razzaRepo.findAll();
    }

    @Override
    public Razza getRazzaById(int id) {
        return razzaRepo.findById(id).orElse(null);
    }

    @Override
    public Razza salva(Razza razza) {
        return razzaRepo.save(razza);
    }

    @Override
    public Razza aggiorna(int id, Razza nuova) {
        return razzaRepo.findById(id).map(r -> {
            r.setNome(nuova.getNome());
            r.setSpecie(nuova.getSpecie());
            return razzaRepo.save(r);
        }).orElse(null);
    }

    @Override
    public void elimina(int id) {
        razzaRepo.deleteById(id);
    }

    @Override
    public List<Razza> getRazzeBySpecie(Specie specie) {
        return razzaRepo.findBySpecie(specie);
    }

    @Override
    public List<Razza> getRazzeBySpecieId(Integer idSpecie) {
        return razzaRepo.findBySpecieId(idSpecie);
    }
}
