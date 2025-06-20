package com.rifugio.rifugio.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Razza")
public class Razza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdRazza;

    @ManyToOne
    @JoinColumn(name = "IdSpecie", referencedColumnName = "IdSpecie")
    private Specie Specie;

    private String Nome;

    public int getIdRazza() {
        return IdRazza;
    }

    public void setIdRazza(int idRazza) {
        IdRazza = idRazza;
    }

    public Specie getSpecie() {
        return Specie;
    }

    public void setSpecie(Specie specie) {
        Specie = specie;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    


}
