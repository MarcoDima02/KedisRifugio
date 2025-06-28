package com.rifugio.rifugio.entities;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="AnagraficaAnimali")
public class AnagraficaAnimali {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id_Animale;

    private String Nome;

    // cascade = CascadeType.ALL permette di creare una nuova cartella clinica se non esiste durante la creazione di un animale
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "Id_Cartella_Clinica", referencedColumnName = "Id_Cartella_Clinica")
    private CartellaClinica Id_Cartella_Clinica;

    @ManyToOne
    @JoinColumn(name = "Id_Specie", referencedColumnName = "Id_Specie")
    private Specie Specie;

    @ManyToOne
    @JoinColumn(name = "Id_Razza", referencedColumnName = "Id_Razza")
    private Razza Razza;

    private char Sesso;
    private Date Data_Nascita;
    private Date Data_Arrivo;
    private String Descrizione_Breve;
    private String Descrizione_Lunga;



    @ManyToOne
    @JoinColumn(name = "Id_Stato_Animale", referencedColumnName = "Id_Stato_Animale")
    private Stato_Animale Id_Stato_Animale;


    
    public CartellaClinica getIdCartellaClinica() {
        return Id_Cartella_Clinica;
    }

    public void setIdCartellaClinica(CartellaClinica idCartellaClinica) {
        Id_Cartella_Clinica = idCartellaClinica;
    }

    public void setStatoAnimale(Stato_Animale statoAnimale) {
        Id_Stato_Animale = statoAnimale;
    }

    public int getIdAnimale() {
        return Id_Animale;
    }

    public void setIdAnimale(int idAnimale) {
        Id_Animale = idAnimale;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Specie getSpecie() {
        return Specie;
    }

    public void setSpecie(Specie specie) {
        this.Specie = specie;
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
        return Data_Nascita;
    }

    public void setDataNascita(Date dataNascita) {
        Data_Nascita = dataNascita;
    }

    public Date getDataArrivo() {
        return Data_Arrivo;
    }

    public void setDataArrivo(Date dataArrivo) {
        Data_Arrivo = dataArrivo;
    }

    public String getDescrizioneBreve() {
        return Descrizione_Breve;
    }

    public void setDescrizioneBreve(String descrizioneBreve) {
        Descrizione_Breve = descrizioneBreve;
    }

    public String getDescrizioneLunga() {
        return Descrizione_Lunga;
    }

    public void setDescrizioneLunga(String descrizioneLunga) {
        Descrizione_Lunga = descrizioneLunga;
    }

    public Stato_Animale getIdStatoAnimale() {
        return Id_Stato_Animale;
    }

    public void setIdStatoAnimale(Stato_Animale statoAnimale) {
        this.Id_Stato_Animale = statoAnimale;
    }



}
