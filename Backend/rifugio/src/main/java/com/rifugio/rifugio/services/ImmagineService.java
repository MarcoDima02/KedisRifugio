package com.rifugio.rifugio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rifugio.rifugio.entities.Immagine;

@Service
public interface ImmagineService {
    
    List<Immagine> getAllImmagini();
    
    Optional<Immagine> getImmagineById(int id);
    
    List<Immagine> getImmaginiByAnimale(int idAnimale);
    
    Immagine storeImmagine(MultipartFile file, int idAnimale);
    
    void deleteImmagine(int id);
}