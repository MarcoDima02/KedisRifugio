package com.rifugio.rifugio.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Donazioni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id_donazione;
    
    private double importo;

    private int id_persona;

    private Date data_donazione;

    private String descrizione;

    public int getId_donazione() {
        return id_donazione;
    }

    public void setId_donazione(int id_donazione) {
        this.id_donazione = id_donazione;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public Date getData_donazione() {
        return data_donazione;
    }

    public void setData_donazione(Date data_donazione) {
        this.data_donazione = data_donazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
