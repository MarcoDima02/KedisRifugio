package com.rifugio.rifugio.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rifugio.rifugio.entities.Donazioni;

public interface DonazioneRepo extends JpaRepository<Donazioni, Integer> {
}
