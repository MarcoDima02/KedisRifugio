package com.rifugio.rifugio.services;

import com.rifugio.rifugio.entities.Razza;
import com.rifugio.rifugio.repos.RazzaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Razza> getRazzaById(int id) {
        return razzaRepo.findById(id);
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
}
