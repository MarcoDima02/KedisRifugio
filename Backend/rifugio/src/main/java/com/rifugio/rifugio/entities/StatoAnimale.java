package com.rifugio.rifugio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "StatoAnimale")
public class StatoAnimale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdStatoAnimale;
    private String Descrizione;

    public int getIdStatoAnimale() {
        return IdStatoAnimale;
    }
    public void setIdStatoAnimale(int idStatoAnimale) {
        IdStatoAnimale = idStatoAnimale;
    }
    public String getDescrizione() {
        return Descrizione;
    }
    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    

}
