package com.rifugio.rifugio.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Razze")
public class Razza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_razza;

    @ManyToOne
    @JoinColumn(name = "idSpecie", referencedColumnName = "id_specie")
    @JsonIgnoreProperties({"razze"}) // evita loop serializzazione se Specie ha lista di razze
    @NotNull(message = "Devi selezionare una specie")
    private Specie id_specie;

    @NotBlank(message = "Il nome è obbligatorio e non può essere vuoto")
    private String nome;

    // Getter & Setter
    public Integer getIdRazza() {
        return id_razza;
    }

    public void setIdRazza(Integer idRazza) {
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
