package com.rifugio.rifugio.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.entities.Immagine;
import com.rifugio.rifugio.repos.AnagraficaAnimaliRepo;
import com.rifugio.rifugio.repos.ImmagineRepo;

@Service
public class ImmagineServiceImpl implements ImmagineService {

    @Autowired
    private ImmagineRepo immagineRepo;
    
    @Autowired
    private AnagraficaAnimaliRepo anagraficaAnimaliRepo;

    @Override
    public List<Immagine> getAllImmagini() {
        return immagineRepo.findAll();
    }

    @Override
    public Optional<Immagine> getImmagineById(int id) {
        return immagineRepo.findById(id);
    }

    @Override
    public List<Immagine> getImmaginiByAnimale(int idAnimale) {
        Optional<AnagraficaAnimali> animale = anagraficaAnimaliRepo.findById(idAnimale);
        if (animale.isPresent()) {
            return immagineRepo.findByAnimale(animale.get());
        }
        return List.of(); // Ritorna una lista vuota se l'animale non esiste
    }

    @Override
    public Immagine storeImmagine(MultipartFile file, int idAnimale) {
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        
        try {
            Optional<AnagraficaAnimali> animale = anagraficaAnimaliRepo.findById(idAnimale);
            if (animale.isPresent()) {
                Immagine immagine = new Immagine(
                    fileName,
                    fileType,
                    file.getBytes(),
                    Date.valueOf(LocalDate.now()),
                    animale.get()
                );
                
                return immagineRepo.save(immagine);
            } else {
                throw new RuntimeException("Animale non trovato con ID: " + idAnimale);
            }
        } catch (IOException e) {
            throw new RuntimeException("Impossibile salvare l'immagine: " + e.getMessage());
        }
    }

    @Override
    public void deleteImmagine(int id) {
        immagineRepo.deleteById(id);
    }

    @Override
    public Immagine updateImmagine(int id, MultipartFile file, int idAnimale) {
        Optional<Immagine> existingOpt = immagineRepo.findById(id);
        if (existingOpt.isEmpty()) {
            throw new RuntimeException("Immagine non trovata con ID: " + id);
        }
        Optional<AnagraficaAnimali> animale = anagraficaAnimaliRepo.findById(idAnimale);
        if (animale.isEmpty()) {
            throw new RuntimeException("Animale non trovato con ID: " + idAnimale);
        }
        Immagine existing = existingOpt.get();
        try {
            existing.setNome(file.getOriginalFilename());
            existing.setTipo(file.getContentType());
            existing.setDati(file.getBytes());
            existing.setData_caricamento(java.sql.Date.valueOf(java.time.LocalDate.now()));
            existing.setAnimale(animale.get());
            return immagineRepo.save(existing);
        } catch (IOException e) {
            throw new RuntimeException("Impossibile aggiornare l'immagine: " + e.getMessage());
        }
    }
}