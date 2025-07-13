package com.rifugio.rifugio.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.Razza;

@Repository
public interface RazzaRepo extends JpaRepository<Razza, Integer> {
    
    // Trova razze per ID specie utilizzando query custom
    @Query("SELECT r FROM Razza r WHERE r.id_specie.Id_Specie = :specieId")
    List<Razza> findBySpecieId(@Param("specieId") Integer specieId);
}
