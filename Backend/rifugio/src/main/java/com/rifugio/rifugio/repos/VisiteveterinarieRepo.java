package com.rifugio.rifugio.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rifugio.rifugio.entities.Visiteveterinarie;

public interface VisiteveterinarieRepo extends JpaRepository<Visiteveterinarie, Integer> {
    List<Visiteveterinarie> findByIdAnimale(int idanimale);

}
