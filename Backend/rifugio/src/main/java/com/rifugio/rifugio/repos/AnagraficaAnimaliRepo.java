package com.rifugio.rifugio.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.AnagraficaAnimali;

@Repository
public interface AnagraficaAnimaliRepo extends JpaRepository<AnagraficaAnimali, Integer>{

}
