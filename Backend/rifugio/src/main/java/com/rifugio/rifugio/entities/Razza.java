package com.rifugio.rifugio.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "Razze")
public class Razza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRazza;

    @ManyToOne
    @JoinColumn(name = "idSpecie", referencedColumnName = "idSpecie")
    @JsonIgnoreProperties({"razze"}) // evita loop serializzazione se Specie ha lista di razze
    private Specie specie;

    private String nome;

    // Getter & Setter
    public int getIdRazza() {
        return idRazza;
    }

    public void setIdRazza(int idRazza) {
        this.idRazza = idRazza;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
