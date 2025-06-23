package com.rifugio.rifugio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Utenti {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id_persona;
   
    private String nome;
   
    private String cognome;
   
    private String codice_fiscale;
   
    private String numero;
   
    private String email;
   
    private String password;
   
    private String ruolo;
   
    

    
    public int getId_persona() {
        return id_persona;
    }
    public void setId_persona(int id_persona) {
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
    public String getCodice_fiscale() {
        return codice_fiscale;
    }
    public void setCodice_fiscale(String codice_fiscale) {
        this.codice_fiscale = codice_fiscale;
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


}

