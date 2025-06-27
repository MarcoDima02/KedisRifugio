package com.rifugio.rifugio.entities;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VisiteVeterinarie {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_visita;

    private Date data;

    private Time ora;

    
    private int id_animale;

    private String motivo;

    private String esito;

    public int getId_visita() {
        return id_visita;
    }

    public void setId_visita(int id_visita) {
        this.id_visita = id_visita;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getOra() {
        return ora;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }

    public int getId_animale() {
        return id_animale;
    }

    public void setId_animale(int id_animale) {
        this.id_animale = id_animale;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEsito() {
        return esito;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }
}
