package com.rifugio.rifugio.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.Adozioni;

@Repository
public interface AdozioniRepo extends JpaRepository<Adozioni, Integer> {
    
    // Trova le ultime adozioni ordinate per data decrescente
    @Query("SELECT a FROM Adozioni a ORDER BY a.dataAdozione DESC")
    List<Adozioni> findUltimeAdozioni(Pageable pageable);
}
