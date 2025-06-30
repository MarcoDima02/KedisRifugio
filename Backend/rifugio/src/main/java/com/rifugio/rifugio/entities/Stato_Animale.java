package com.rifugio.rifugio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Stato_Animale")
public class Stato_Animale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Stato_Animale;
    private String Descrizione;

    public Integer getIdStatoAnimale() {
        return Id_Stato_Animale;
    }
    public void setIdStatoAnimale(Integer idStatoAnimale) {
        Id_Stato_Animale = idStatoAnimale;
    }
    public String getDescrizione() {
        return Descrizione;
    }
    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    

}
