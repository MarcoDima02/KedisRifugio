package com.rifugio.rifugio.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.Utenti;

@Repository
public interface UtentiRepo extends JpaRepository<Utenti, Integer> {

    // Custom query methods
    Optional<Utenti> findByEmail(String email);
    Optional<Utenti> findByCodiceFiscale(String codiceFiscale);
    boolean existsByEmail(String email);
    boolean existsByCodiceFiscale(String codiceFiscale);
    
    // Metodi per soft delete - solo utenti attivi
    @Query("SELECT u FROM Utenti u WHERE u.attivo = true")
    List<Utenti> findAllActive();
    
    @Query("SELECT u FROM Utenti u WHERE u.id_persona = :id AND u.attivo = true")
    Optional<Utenti> findActiveById(@Param("id") Integer id);
    
    @Query("SELECT u FROM Utenti u WHERE u.email = :email AND u.attivo = true")
    Optional<Utenti> findActiveByEmail(@Param("email") String email);
    
    @Query("SELECT u FROM Utenti u WHERE u.codiceFiscale = :codiceFiscale AND u.attivo = true")
    Optional<Utenti> findActiveByCodiceFiscale(@Param("codiceFiscale") String codiceFiscale);
    
    // Verifica esistenza solo tra utenti attivi
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Utenti u WHERE u.email = :email AND u.attivo = true")
    boolean existsActiveByEmail(@Param("email") String email);
    
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Utenti u WHERE u.codiceFiscale = :codiceFiscale AND u.attivo = true")
    boolean existsActiveByCodiceFiscale(@Param("codiceFiscale") String codiceFiscale);

}
