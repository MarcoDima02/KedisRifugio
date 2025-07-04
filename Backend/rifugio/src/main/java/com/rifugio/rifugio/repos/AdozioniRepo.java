package com.rifugio.rifugio.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.Adozioni;

@Repository
public interface AdozioniRepo extends JpaRepository<Adozioni, Integer> {
}
