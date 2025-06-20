package com.rifugio.rifugio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "StatoAnimale")
public class StatoAnimale {

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
