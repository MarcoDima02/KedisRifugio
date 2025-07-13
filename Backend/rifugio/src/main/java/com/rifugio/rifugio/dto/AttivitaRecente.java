package com.rifugio.rifugio.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AttivitaRecente {
    private LocalDateTime dataOra;
    private String tipo;
    private String dettagli;
    private String stato;
    private String badgeClass;

    public AttivitaRecente() {}

    public AttivitaRecente(LocalDateTime dataOra, String tipo, String dettagli, String stato, String badgeClass) {
        this.dataOra = dataOra;
        this.tipo = tipo;
        this.dettagli = dettagli;
        this.stato = stato;
        this.badgeClass = badgeClass;
    }
    
    // Metodo per formattare la data in modo leggibile
    public String getDataOraFormatted() {
        if (dataOra != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return dataOra.format(formatter);
        }
        return "";
    }

    // Getters and Setters
    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDettagli() {
        return dettagli;
    }

    public void setDettagli(String dettagli) {
        this.dettagli = dettagli;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getBadgeClass() {
        return badgeClass;
    }

    public void setBadgeClass(String badgeClass) {
        this.badgeClass = badgeClass;
    }
}
