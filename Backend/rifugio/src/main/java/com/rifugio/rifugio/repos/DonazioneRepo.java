package com.rifugio.rifugio.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.Donazioni;

@Repository
public interface DonazioneRepo extends JpaRepository<Donazioni, Integer> {
}
