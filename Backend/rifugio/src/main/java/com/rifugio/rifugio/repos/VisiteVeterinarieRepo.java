package com.rifugio.rifugio.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.VisiteVeterinarie;

@Repository
public interface VisiteVeterinarieRepo extends JpaRepository<VisiteVeterinarie, Integer> {
    
    // Trova le ultime visite completate (passate) ordinate per data e ora decrescente
    @Query("SELECT v FROM VisiteVeterinarie v WHERE v.data < :oggi ORDER BY v.data DESC, v.ora DESC")
    List<VisiteVeterinarie> findUltimeVisite(@Param("oggi") LocalDate oggi, Pageable pageable);
    
    // Trova le prossime visite (future o di oggi) ordinate per data e ora crescente
    @Query("SELECT v FROM VisiteVeterinarie v WHERE v.data >= :oggi ORDER BY v.data ASC, v.ora ASC")
    List<VisiteVeterinarie> findProssimeVisite(@Param("oggi") LocalDate oggi, Pageable pageable);
}
