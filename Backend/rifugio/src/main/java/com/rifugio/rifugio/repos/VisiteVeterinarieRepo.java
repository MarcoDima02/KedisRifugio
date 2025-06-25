package com.rifugio.rifugio.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.VisiteVeterinarie;

@Repository
public interface VisiteVeterinarieRepo extends JpaRepository<VisiteVeterinarie, Integer> {
    
}
