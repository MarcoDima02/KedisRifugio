package com.rifugio.rifugio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Specie")
public class Specie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Specie;
    private String Nome;
    
    public Integer getIdSpecie() {
        return Id_Specie;
    }
    public void setIdSpecie(Integer idSpecie) {
        Id_Specie = idSpecie;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }



}
