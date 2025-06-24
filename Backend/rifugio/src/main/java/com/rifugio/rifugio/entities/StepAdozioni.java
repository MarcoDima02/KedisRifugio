package com.rifugio.rifugio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StepAdozioni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id_step_adozioni;

    private String nome_step;

    private String descrizione_step;

    public int getId_step_adozioni() {
        return id_step_adozioni;
    }

    public void setId_step_adozioni(int id_step_adozioni) {
        this.id_step_adozioni = id_step_adozioni;
    }

    public String getNome_step() {
        return nome_step;
    }

    public void setNome_step(String nome_step) {
        this.nome_step = nome_step;
    }

    public String getDescrizione_step() {
        return descrizione_step;
    }

    public void setDescrizione_step(String descrizione_step) {
        this.descrizione_step = descrizione_step;
    }

}
