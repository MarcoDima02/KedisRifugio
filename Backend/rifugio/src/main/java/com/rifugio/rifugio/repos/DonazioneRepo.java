package com.rifugio.rifugio.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.entities.Utenti;

@Repository
public interface DonazioneRepo extends JpaRepository<Donazioni, Integer> {

    List<Donazioni> findByIdPersona(Utenti id);

}
