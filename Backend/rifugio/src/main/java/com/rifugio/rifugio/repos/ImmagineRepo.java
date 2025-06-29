package com.rifugio.rifugio.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.Immagine;
import com.rifugio.rifugio.entities.AnagraficaAnimali;;

@Repository
public interface ImmagineRepo extends JpaRepository<Immagine, Integer> {
    List<Immagine> findByAnimale(AnagraficaAnimali animale);
}