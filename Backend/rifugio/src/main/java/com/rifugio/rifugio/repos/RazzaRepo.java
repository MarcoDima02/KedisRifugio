package com.rifugio.rifugio.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.Razza;

@Repository
public interface RazzaRepo extends JpaRepository<Razza, Integer> {
}
