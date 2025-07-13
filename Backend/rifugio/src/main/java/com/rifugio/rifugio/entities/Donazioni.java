package com.rifugio.rifugio.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Donazioni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id_donazione;
    
    private double importo;

    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "Id_Persona")
    private Utenti persona;

    private Date data_donazione;

    private String descrizione;
    
    private String metodo_pagamento;
    
    private String note;

    // Costruttore vuoto richiesto per JPA e Thymeleaf
    public Donazioni() {
    }

    public Integer getId_donazione() {
        return id_donazione;
    }

    public void setId_donazione(Integer id_donazione) {
        this.id_donazione = id_donazione;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public Utenti getPersona() {
        return persona;
    }

    public void setPersona(Utenti persona) {
        this.persona = persona;
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
    
    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public void setMetodo_pagamento(String metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    @Override
    public String toString() {
        return "Donazioni{" +
                "id_donazione=" + id_donazione +
                ", importo=" + importo +
                ", persona=" + (persona != null ? persona.getId_persona() : null) +
                ", data_donazione=" + data_donazione +
                ", descrizione='" + descrizione + '\'' +
                ", metodo_pagamento='" + metodo_pagamento + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
