package com.rifugio.rifugio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "visiteveterinarie")
public class Visiteveterinarie {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idvisita;
    private String data;
    private String ora;
    private String idanimale;
    private String motivo;
    private String esito;
    public int getIdvisita() {
        return idvisita;
    }
    public void setIdvisita(int idvisita) {
        this.idvisita = idvisita;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getOra() {
        return ora;
    }
    public void setOra(String ora) {
        this.ora = ora;
    }
    public String getIdanimale() {
        return idanimale;
    }
    public void setIdanimale(String idanimale) {
        this.idanimale = idanimale;
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
