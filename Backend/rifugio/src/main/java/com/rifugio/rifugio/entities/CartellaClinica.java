package com.rifugio.rifugio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartellaClinica")
public class CartellaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdCartellaClinica;
    @ManyToOne
    @JoinColumn(name = "IdAnimale", referencedColumnName = "IdAnimale")
    private AnagraficaAnimali IdAnimale;
    private boolean Sterilizzato;
    private boolean Vaccinato;
    private String Microchip;
    private boolean Sverminazione;
    private boolean TrattamentoAntiparassitario;

    public int getIdCartellaClinica() {
        return IdCartellaClinica;
    }
    public void setIdCartellaClinica(int idCartellaClinica) {
        IdCartellaClinica = idCartellaClinica;
    }
    public AnagraficaAnimali getIdAnimale() {
        return IdAnimale;
    }
    public void setIdAnimale(AnagraficaAnimali idAnimale) {
        IdAnimale = idAnimale;
    }
    public boolean isSterilizzato() {
        return Sterilizzato;
    }
    public void setSterilizzato(boolean sterilizzato) {
        Sterilizzato = sterilizzato;
    }
    public boolean isVaccinato() {
        return Vaccinato;
    }
    public void setVaccinato(boolean vaccinato) {
        Vaccinato = vaccinato;
    }
    public String getMicrochip() {
        return Microchip;
    }
    public void setMicrochip(String microchip) {
        Microchip = microchip;
    }
    public boolean isSverminazione() {
        return Sverminazione;
    }
    public void setSverminazione(boolean sverminazione) {
        Sverminazione = sverminazione;
    }
    public boolean isTrattamentoAntiparassitario() {
        return TrattamentoAntiparassitario;
    }
    public void setTrattamentoAntiparassitario(boolean trattamentoAntiparassitario) {
        TrattamentoAntiparassitario = trattamentoAntiparassitario;
    }

    


}
