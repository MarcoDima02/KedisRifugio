package com.rifugio.rifugio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Razze")
public class Razza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id_Razza;

    @ManyToOne
    @JoinColumn(name = "Id_Specie", referencedColumnName = "Id_Specie")
    private Specie Specie;

    private String Nome;

    public int getIdRazza() {
        return Id_Razza;
    }

    public void setIdRazza(int idRazza) {
        Id_Razza = idRazza;
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
