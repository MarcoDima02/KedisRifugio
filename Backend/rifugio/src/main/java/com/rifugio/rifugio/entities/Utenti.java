package com.rifugio.rifugio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Utenti {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_persona;
   
    private String nome;
   
    private String cognome;
   
    private String codiceFiscale;
   
    private String numero;
   
    private String email;
   
    private String password;
   
    private String ruolo;
    
    // Campo per soft delete - true = attivo, false = eliminato logicamente
    @Column(name = "attivo", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean attivo = true;
   
    // Costruttore predefinito necessario per JPA e Spring
    public Utenti() {
    }

    
    public Integer getId_persona() {
        return id_persona;
    }
    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRuolo() {
        return ruolo;
    }
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Boolean getAttivo() {
        return attivo;
    }
    
    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }
    
    // Metodi di utilità per soft delete
    public void elimina() {
        this.attivo = false;
    }
    
    public void ripristina() {
        this.attivo = true;
    }
    
    public boolean isEliminato() {
        return this.attivo == null || !this.attivo;
    }

    @Override
    public String toString() {
        return "Utenti{" +
                "id_persona=" + id_persona +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", attivo=" + attivo +
                '}';
    }

}

