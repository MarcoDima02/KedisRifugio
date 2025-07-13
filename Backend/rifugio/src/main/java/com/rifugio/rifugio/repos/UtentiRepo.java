package com.rifugio.rifugio.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.Utenti;

@Repository
public interface UtentiRepo extends JpaRepository<Utenti, Integer> {

    // Custom query methods
    Optional<Utenti> findByEmail(String email);
    Optional<Utenti> findByCodiceFiscale(String codiceFiscale);
    boolean existsByEmail(String email);
    boolean existsByCodiceFiscale(String codiceFiscale);

}
