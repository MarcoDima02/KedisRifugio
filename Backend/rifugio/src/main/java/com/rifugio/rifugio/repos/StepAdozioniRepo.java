package com.rifugio.rifugio.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.StepAdozioni;

@Repository
public interface StepAdozioniRepo extends JpaRepository<StepAdozioni, Integer> {
    
    // Define any custom query methods if needed
    // For example:
    // List<StepAdozioni> findBySomeCriteria(String criteria);

}
