package com.rifugio.rifugio.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VisiteVeterinarie {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_visita;

    private LocalDate data;

    private LocalTime ora;

    @ManyToOne
    @JoinColumn (name = "id_animale", referencedColumnName = "id_animale")
    private AnagraficaAnimali id_animale;

    private String motivo;

    private String esito;

    public Integer getId_visita() {
        return id_visita;
    }

    public void setId_visita(Integer id_visita) {
        this.id_visita = id_visita;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public AnagraficaAnimali getId_animale() {
        return id_animale;
    }

    public void setId_animale(AnagraficaAnimali id_animale) {
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
