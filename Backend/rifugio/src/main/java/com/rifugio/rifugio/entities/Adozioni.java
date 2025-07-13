package com.rifugio.rifugio.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "adozioni")
public class Adozioni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAdozione")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_animale", referencedColumnName = "id_animale")
    private AnagraficaAnimali animale;

    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Utenti persona;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_adozione")
    private Date dataAdozione;

    @ManyToOne
    @JoinColumn(name = "id_step_adozioni", referencedColumnName = "id_step_adozioni")
    private StepAdozioni stepAdozione;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AnagraficaAnimali getAnimale() {
        return animale;
    }

    public void setAnimale(AnagraficaAnimali animale) {
        this.animale = animale;
    }

    public Utenti getPersona() {
        return persona;
    }

    public void setPersona(Utenti persona) {
        this.persona = persona;
    }

    public Date getDataAdozione() {
        return dataAdozione;
    }

    public void setDataAdozione(Date dataAdozione) {
        this.dataAdozione = dataAdozione;
    }

    public StepAdozioni getStepAdozione() {
        return stepAdozione;
    }

    public void setStepAdozione(StepAdozioni stepAdozione) {
        this.stepAdozione = stepAdozione;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
