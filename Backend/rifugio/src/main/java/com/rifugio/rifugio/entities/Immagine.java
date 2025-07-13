package com.rifugio.rifugio.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
    private AnagraficaAnimali animale;
    
    private boolean is_principale = false;
    private int ordine_visualizzazione = 0;
    
    // Constructors
    public Immagine() {
    }
    
    public Immagine(String nome, String tipo, byte[] dati, Date data_caricamento, AnagraficaAnimali animale) {
        this.nome = nome;
        this.tipo = tipo;
        this.dati = dati;
        this.data_caricamento = data_caricamento;
        this.animale = animale;
        this.is_principale = false;
        this.ordine_visualizzazione = 0;
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

    public AnagraficaAnimali getAnimale() {
        return animale;
    }

    public void setAnimale(AnagraficaAnimali animale) {
        this.animale = animale;
    }

    public boolean isIs_principale() {
        return is_principale;
    }

    public void setIs_principale(boolean is_principale) {
        this.is_principale = is_principale;
    }

    public int getOrdine_visualizzazione() {
        return ordine_visualizzazione;
    }

    public void setOrdine_visualizzazione(int ordine_visualizzazione) {
        this.ordine_visualizzazione = ordine_visualizzazione;
    }
}