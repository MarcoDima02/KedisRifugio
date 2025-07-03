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
    @JoinColumn(name = "Id_Persona", referencedColumnName = "Id_Persona")
    private Utenti idPersona;

    private Date data_donazione;

    private String descrizione;

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

    public Utenti getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Utenti idPersona) {
        this.idPersona = idPersona;
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
