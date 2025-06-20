package com.rifugio.rifugio.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="AnagraficaAnimali")
public class AnagraficaAnimali {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdAnimale;

    private String Nome;

    @ManyToOne
    @JoinColumn(name = "IdSpecie", referencedColumnName = "IdSpecie")
    private Specie specie;

    @ManyToOne
    @JoinColumn(name = "IdRazza", referencedColumnName = "IdRazza")
    private Razza Razza;

    private char Sesso;
    private Date DataNascita;
    private Date DataArrivo;
    private String DescrizioneBreve;
    private String DescrizioneLunga;
    private Boolean Vaccinato;
    private Boolean Sterilizzato;
    private Boolean Microchip;

    @ManyToOne
    @JoinColumn(name = "IdStato", referencedColumnName = "IdStato")
    private StatoAnimale StatoAnimale;

    public int getIdAnimale() {
        return IdAnimale;
    }

    public void setIdAnimale(int idAnimale) {
        IdAnimale = idAnimale;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public Razza getRazza() {
        return Razza;
    }

    public void setRazza(Razza razza) {
        this.Razza = razza;
    }

    public char getSesso() {
        return Sesso;
    }

    public void setSesso(char sesso) {
        Sesso = sesso;
    }

    public Date getDataNascita() {
        return DataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        DataNascita = dataNascita;
    }

    public Date getDataArrivo() {
        return DataArrivo;
    }

    public void setDataArrivo(Date dataArrivo) {
        DataArrivo = dataArrivo;
    }

    public String getDescrizioneBreve() {
        return DescrizioneBreve;
    }

    public void setDescrizioneBreve(String descrizioneBreve) {
        DescrizioneBreve = descrizioneBreve;
    }

    public String getDescrizioneLunga() {
        return DescrizioneLunga;
    }

    public void setDescrizioneLunga(String descrizioneLunga) {
        DescrizioneLunga = descrizioneLunga;
    }

    public Boolean getVaccinato() {
        return Vaccinato;
    }

    public void setVaccinato(Boolean vaccinato) {
        Vaccinato = vaccinato;
    }

    public Boolean getSterilizzato() {
        return Sterilizzato;
    }

    public void setSterilizzato(Boolean sterilizzato) {
        Sterilizzato = sterilizzato;
    }

    public Boolean getMicrochip() {
        return Microchip;
    }

    public void setMicrochip(Boolean microchip) {
        Microchip = microchip;
    }

    public StatoAnimale getStatoAnimale() {
        return StatoAnimale;
    }

    public void setStato(StatoAnimale statoAnimale) {
        this.StatoAnimale = statoAnimale;
    }

    

}
