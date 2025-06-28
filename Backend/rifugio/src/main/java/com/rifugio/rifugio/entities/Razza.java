package com.rifugio.rifugio.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "Razze")
public class Razza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_razza;

    @ManyToOne
    @JoinColumn(name = "idSpecie", referencedColumnName = "id_specie")
    @JsonIgnoreProperties({"razze"}) // evita loop serializzazione se Specie ha lista di razze

    private Specie id_specie;

    private String nome;

    // Getter & Setter
    public int getIdRazza() {
        return id_razza;
    }

    public void setIdRazza(int idRazza) {
        this.id_razza = idRazza;
    }

    public Specie getSpecie() {
        return id_specie;
    }

    public void setSpecie(Specie specie) {
        this.id_specie = specie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
