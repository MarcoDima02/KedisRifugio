package com.rifugio.rifugio.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Adozioni {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_adozione;

    @ManyToOne
    @JoinColumn(name = "id_animale", referencedColumnName = "id_animale")
    private AnagraficaAnimali id_animale;

    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Utenti id_persona;

    private Date data_adozione;

    @OneToMany
    @JoinColumn(name = "id_step_adozioni", referencedColumnName = "id_step_adozioni")
    private StepAdozioni id_step_adozioni;


    public Integer getId_adozione() {
        return id_adozione;
    }

    public void setId_adozione(Integer id_adozione) {
        this.id_adozione = id_adozione;
    }

    public AnagraficaAnimali getId_animale() {
        return id_animale;
    }

    public void setId_animale(AnagraficaAnimali id_animale) {
        this.id_animale = id_animale;
    }

    public Utenti getId_persona() {
        return id_persona;
    }

    public void setId_persona(Utenti id_persona) {
        this.id_persona = id_persona;
    }

    public Date getData_adozione() {
        return data_adozione;
    }

    public void setData_adozione(Date data_adozione) {
        this.data_adozione = data_adozione;
    }

    public StepAdozioni getId_step_adozioni() {
        return id_step_adozioni;
    }

    public void setId_step_adozioni(StepAdozioni id_step_adozioni) {
        this.id_step_adozioni = id_step_adozioni;
    }


}
