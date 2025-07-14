package com.rifugio.rifugio.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.rifugio.rifugio.entities.Donazioni;

@Repository
public interface DonazioneRepo extends JpaRepository<Donazioni, Integer> {
    
    // Trova le ultime donazioni ordinate per data e ora decrescente
    @Query("SELECT d FROM Donazioni d ORDER BY d.data_donazione DESC")
    List<Donazioni> findUltimeDonazioni(Pageable pageable);
    
    // Trova tutte le donazioni di un utente specifico ordinate per data decrescente
    @Query("SELECT d FROM Donazioni d WHERE d.persona.id_persona = :idUtente ORDER BY d.data_donazione DESC")
    List<Donazioni> findByPersonaIdPersonaOrderByDataDonazioneDesc(@Param("idUtente") Integer idUtente);
}
