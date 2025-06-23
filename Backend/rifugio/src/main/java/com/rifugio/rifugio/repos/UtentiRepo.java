package com.rifugio.rifugio.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.Utenti;

@Repository
public interface UtentiRepo extends JpaRepository<Utenti, Integer> {

    // Custom query methods can be defined here if needed
    // For example, to find a user by email:
    // Optional<Utenti> findByEmail(String email);

}
