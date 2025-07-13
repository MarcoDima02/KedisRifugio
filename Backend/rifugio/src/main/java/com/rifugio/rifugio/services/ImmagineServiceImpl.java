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
    public List<Immagine> getImmaginiByAnimaleOrdered(int idAnimale) {
        Optional<AnagraficaAnimali> animale = anagraficaAnimaliRepo.findById(idAnimale);
        if (animale.isPresent()) {
            return immagineRepo.findByAnimaleOrderByOrdineVisualizzazione(animale.get());
        }
        return List.of();
    }

    @Override
    public Optional<Immagine> getImmaginePrincipaleByAnimale(int idAnimale) {
        Optional<AnagraficaAnimali> animale = anagraficaAnimaliRepo.findById(idAnimale);
        if (animale.isPresent()) {
            return immagineRepo.findImmaginePrincipaleByAnimale(animale.get());
        }
        return Optional.empty();
    }

    @Override
    public List<Immagine> getImmaginiSecondariesByAnimale(int idAnimale) {
        Optional<AnagraficaAnimali> animale = anagraficaAnimaliRepo.findById(idAnimale);
        if (animale.isPresent()) {
            return immagineRepo.findImmaginiSecondariesByAnimale(animale.get());
        }
        return List.of();
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
    public List<Immagine> storeImmagini(List<MultipartFile> files, int idAnimale) {
        Optional<AnagraficaAnimali> animale = anagraficaAnimaliRepo.findById(idAnimale);
        if (animale.isEmpty()) {
            throw new RuntimeException("Animale non trovato con ID: " + idAnimale);
        }
        List<Immagine> immaginiSalvate = new java.util.ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String fileName = file.getOriginalFilename();
            String fileType = file.getContentType();
            try {
                Immagine immagine = new Immagine(
                    fileName,
                    fileType,
                    file.getBytes(),
                    java.sql.Date.valueOf(java.time.LocalDate.now()),
                    animale.get()
                );
                // Imposta l'ordine di visualizzazione in base all'indice
                immagine.setOrdine_visualizzazione(i);
                immaginiSalvate.add(immagineRepo.save(immagine));
            } catch (IOException e) {
                throw new RuntimeException("Impossibile salvare l'immagine: " + fileName + ", errore: " + e.getMessage());
            }
        }
        return immaginiSalvate;
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

    @Override
    public void setImmaginePrincipale(int idImmagine, int idAnimale) {
        Optional<AnagraficaAnimali> animale = anagraficaAnimaliRepo.findById(idAnimale);
        if (animale.isPresent()) {
            // Prima rimuovi il flag principale da tutte le immagini dell'animale
            List<Immagine> tutteImmagini = immagineRepo.findByAnimale(animale.get());
            for (Immagine img : tutteImmagini) {
                img.setIs_principale(false);
                immagineRepo.save(img);
            }
            
            // Poi imposta la nuova immagine principale
            Optional<Immagine> nuovaPrincipale = immagineRepo.findById(idImmagine);
            if (nuovaPrincipale.isPresent()) {
                nuovaPrincipale.get().setIs_principale(true);
                immagineRepo.save(nuovaPrincipale.get());
            }
        }
    }

    @Override
    public void updateOrdineVisualizzazione(List<Integer> ordineImmagini) {
        for (int i = 0; i < ordineImmagini.size(); i++) {
            Optional<Immagine> immagine = immagineRepo.findById(ordineImmagini.get(i));
            if (immagine.isPresent()) {
                immagine.get().setOrdine_visualizzazione(i + 1);
                immagineRepo.save(immagine.get());
            }
        }
    }
}