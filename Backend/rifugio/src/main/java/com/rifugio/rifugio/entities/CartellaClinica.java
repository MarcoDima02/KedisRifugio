package com.rifugio.rifugio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cartella_Clinica")
public class CartellaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Cartella_Clinica;
    private boolean Sterilizzato;
    private Integer Vaccini;
    private String Microchip;
    private boolean Sverminazione;
    private boolean Trattamento_Antiparassitario;

    public Integer getIdCartellaClinica() {
        return Id_Cartella_Clinica;
    }
    public void setIdCartellaClinica(Integer idCartellaClinica) {
        Id_Cartella_Clinica = idCartellaClinica;
    }

    public boolean isSterilizzato() {
        return Sterilizzato;
    }
    public void setSterilizzato(boolean sterilizzato) {
        Sterilizzato = sterilizzato;
    }
    public Integer getVaccinazioni() {
        return Vaccini;
    }
    public void setVaccinazioni(Integer vaccinazioni) {
        Vaccini = vaccinazioni;
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
        return Trattamento_Antiparassitario;
    }
    public void setTrattamentoAntiparassitario(boolean trattamentoAntiparassitario) {
        Trattamento_Antiparassitario = trattamentoAntiparassitario;
    }

    


}
