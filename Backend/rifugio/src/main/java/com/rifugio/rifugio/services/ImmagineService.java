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
    
    List<Immagine> getImmaginiByAnimaleOrdered(int idAnimale);
    
    Optional<Immagine> getImmaginePrincipaleByAnimale(int idAnimale);
    
    List<Immagine> getImmaginiSecondariesByAnimale(int idAnimale);
    
    Immagine storeImmagine(MultipartFile file, int idAnimale);
    
    List<Immagine> storeImmagini(List<MultipartFile> files, int idAnimale);
    
    void deleteImmagine(int id);
    
    Immagine updateImmagine(int id, MultipartFile file, int idAnimale);
    
    void setImmaginePrincipale(int idImmagine, int idAnimale);
    
    void updateOrdineVisualizzazione(List<Integer> ordineImmagini);
}