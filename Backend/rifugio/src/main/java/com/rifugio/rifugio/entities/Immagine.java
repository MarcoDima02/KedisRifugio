package com.rifugio.rifugio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="immagini")
public class Immagine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_immagine;

    private String nome;
    private String tipo;
    
    @Lob
    private byte[] dati;
    
    private Date data_caricamento;
    
    @ManyToOne
    @JoinColumn(name = "id_animale", referencedColumnName = "id_animale")
    private Anagrafica_Animali animale;
    
    // Constructors
    public Immagine() {
    }
    
    public Immagine(String nome, String tipo, byte[] dati, Date data_caricamento, Anagrafica_Animali animale) {
        this.nome = nome;
        this.tipo = tipo;
        this.dati = dati;
        this.data_caricamento = data_caricamento;
        this.animale = animale;
    }
    
    // Getters and Setters
    public int getId_immagine() {
        return id_immagine;
    }

    public void setId_immagine(int id_immagine) {
        this.id_immagine = id_immagine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getDati() {
        return dati;
    }

    public void setDati(byte[] dati) {
        this.dati = dati;
    }

    public Date getData_caricamento() {
        return data_caricamento;
    }

    public void setData_caricamento(Date data_caricamento) {
        this.data_caricamento = data_caricamento;
    }

    public Anagrafica_Animali getAnimale() {
        return animale;
    }

    public void setAnimale(Anagrafica_Animali animale) {
        this.animale = animale;
    }
}