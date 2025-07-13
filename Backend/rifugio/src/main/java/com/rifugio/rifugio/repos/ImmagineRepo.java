package com.rifugio.rifugio.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.entities.Immagine;

@Repository
public interface ImmagineRepo extends JpaRepository<Immagine, Integer> {
    List<Immagine> findByAnimale(AnagraficaAnimali animale);
    
    @Query("SELECT i FROM Immagine i WHERE i.animale = :animale ORDER BY i.ordine_visualizzazione ASC, i.id_immagine ASC")
    List<Immagine> findByAnimaleOrderByOrdineVisualizzazione(@Param("animale") AnagraficaAnimali animale);
    
    @Query("SELECT i FROM Immagine i WHERE i.animale = :animale AND i.is_principale = true")
    Optional<Immagine> findImmaginePrincipaleByAnimale(@Param("animale") AnagraficaAnimali animale);
    
    @Query("SELECT i FROM Immagine i WHERE i.animale = :animale AND i.is_principale = false ORDER BY i.ordine_visualizzazione ASC, i.id_immagine ASC")
    List<Immagine> findImmaginiSecondariesByAnimale(@Param("animale") AnagraficaAnimali animale);
}