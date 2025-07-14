package com.rifugio.rifugio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Cartella_Clinica")
public class CartellaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Cartella_Clinica;
    private boolean Sterilizzato;
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer Vaccini;

    @Pattern(regexp = "^$|\\d{15}", message = "Il microchip deve essere vuoto oppure contenere esattamente 15 cifre")
    private String Microchip;
    private boolean Sverminazione;
    private boolean Trattamento_Antiparassitario;

    // Costruttore di default che inizializza i campi
    public CartellaClinica() {
        this.Vaccini = 0; // Inizializza il campo Vaccini a 0 per evitare valori null
        this.Sterilizzato = false;
        this.Sverminazione = false;
        this.Trattamento_Antiparassitario = false;
    }

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
        Vaccini = vaccinazioni != null ? vaccinazioni : 0;
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
