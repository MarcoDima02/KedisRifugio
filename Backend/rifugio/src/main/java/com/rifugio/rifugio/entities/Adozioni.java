package com.rifugio.rifugio.entities;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Adozioni {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_adozione;

    private Integer id_animale;

    private Integer id_persona;

    private Date data_adozione;

    private Integer id_step_adozioni;


    public Integer getId_adozione() {
        return id_adozione;
    }

    public void setId_adozione(Integer id_adozione) {
        this.id_adozione = id_adozione;
    }

    public Integer getId_animale() {
        return id_animale;
    }

    public void setId_animale(Integer id_animale) {
        this.id_animale = id_animale;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public Date getData_adozione() {
        return data_adozione;
    }

    public void setData_adozione(Date data_adozione) {
        this.data_adozione = data_adozione;
    }

    public Integer getId_step_adozioni() {
        return id_step_adozioni;
    }

    public void setId_step_adozioni(Integer id_step_adozioni) {
        this.id_step_adozioni = id_step_adozioni;
    }


}
