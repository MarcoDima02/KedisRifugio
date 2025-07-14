package com.rifugio.rifugio.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rifugio.rifugio.entities.Razza;
import com.rifugio.rifugio.entities.Specie;

@Repository
public interface RazzaRepo extends JpaRepository<Razza, Integer> {
    
    // Trova tutte le razze per una specie specifica
    @Query("SELECT r FROM Razza r WHERE r.id_specie = :specie ORDER BY r.nome ASC")
    List<Razza> findBySpecie(@Param("specie") Specie specie);
    
    // Trova tutte le razze per ID specie
    @Query("SELECT r FROM Razza r WHERE r.id_specie.Id_Specie = :idSpecie ORDER BY r.nome ASC")
    List<Razza> findBySpecieId(@Param("idSpecie") Integer idSpecie);
}
