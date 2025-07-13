-- Database rifugio_animali - MySQL

-- Eliminazione tabelle esistenti (ordine corretto)
DROP TABLE IF EXISTS visite_veterinarie;
DROP TABLE IF EXISTS adozioni;
DROP TABLE IF EXISTS donazioni;
DROP TABLE IF EXISTS anagrafica_animali;
DROP TABLE IF EXISTS cartella_clinica;
DROP TABLE IF EXISTS razze;
DROP TABLE IF EXISTS specie;
DROP TABLE IF EXISTS stato_animale;
DROP TABLE IF EXISTS step_adozioni;
DROP TABLE IF EXISTS utenti;

-- Creazione tabelle indipendenti
CREATE TABLE stato_animale (
    id_stato_animale INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    descrizione VARCHAR(50) NOT NULL
);

CREATE TABLE specie (
    id_specie INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(50) NOT NULL
);

-- Tabelle con foreign key di primo livello
CREATE TABLE razze (
    id_razza INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_specie INT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_specie) REFERENCES specie(id_specie)
);

-- Creazione tabella cartella_clinica
CREATE TABLE cartella_clinica (
    id_cartella_clinica INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    sterilizzato BOOLEAN DEFAULT FALSE,
    vaccini INT NOT NULL,
    microchip VARCHAR(50),
    sverminazione BOOLEAN DEFAULT FALSE,
    trattamento_antiparassitario BOOLEAN DEFAULT FALSE
);

-- Creazione tabella anagrafica_animali
CREATE TABLE anagrafica_animali (
    id_animale INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    id_specie INT NOT NULL,
    id_razza INT NULL,
    sesso CHAR(1) NOT NULL CHECK (sesso IN ('M', 'F')),
    data_nascita DATE NOT NULL,
    data_arrivo DATE NOT NULL,
    descrizione_breve VARCHAR(100),
    descrizione_lunga VARCHAR(400),
    id_cartella_clinica INT NOT NULL,
    id_stato_animale INT NOT NULL,
    FOREIGN KEY (id_specie) REFERENCES specie(id_specie),
    FOREIGN KEY (id_razza) REFERENCES razze(id_razza),
    FOREIGN KEY (id_cartella_clinica) REFERENCES cartella_clinica(id_cartella_clinica),
    FOREIGN KEY (id_stato_animale) REFERENCES stato_animale(id_stato_animale)
);

-- Creazione tabella utenti
CREATE TABLE utenti (
    id_persona INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    codice_fiscale VARCHAR(16) NOT NULL UNIQUE,
    numero VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(20) NOT NULL,
    ruolo ENUM('ADMIN', 'USER') Not null DEFAULT 'USER',
    attivo BOOLEAN DEFAULT TRUE NOT NULL
);

-- Creazione tabella step_adozioni
CREATE TABLE step_adozioni (
    id_step_adozioni INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome_step VARCHAR(30) NOT NULL,
    descrizione_step VARCHAR(150) NOT NULL
);

-- Creazione tabella donazioni
CREATE TABLE donazioni (
    id_donazione INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    importo DECIMAL(10,2) NOT NULL,
    id_persona INT NOT NULL,
    data_donazione DATE NOT NULL,
    descrizione VARCHAR(150),
    FOREIGN KEY (id_persona) REFERENCES utenti(id_persona)
);

-- Creazione tabella adozioni
CREATE TABLE adozioni (
    id_adozione INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_animale INT NOT NULL,
    id_persona INT NOT NULL,
    data_adozione DATE NOT NULL,
    id_step_adozioni INT NULL,
    note TEXT,
    FOREIGN KEY (id_animale) REFERENCES anagrafica_animali(id_animale),
    FOREIGN KEY (id_persona) REFERENCES utenti(id_persona),
    FOREIGN KEY (id_step_adozioni) REFERENCES step_adozioni(id_step_adozioni)
);

-- Creazione tabella visite_veterinarie
CREATE TABLE visite_veterinarie (
    id_visita INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    data DATE NOT NULL,
    ora TIME NOT NULL,
    id_animale INT NOT NULL,
    motivo VARCHAR(150) NOT NULL,
    esito VARCHAR(150) NOT NULL Default 'In attesa',
    FOREIGN KEY (id_animale) REFERENCES anagrafica_animali(id_animale)
);

-- Creazione tabella immagini
CREATE TABLE immagini (
    id_immagine INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    dati LONGBLOB NOT NULL,
    data_caricamento DATE NOT NULL,
    id_animale INT,
    is_principale BOOLEAN DEFAULT FALSE,
    ordine_visualizzazione INT DEFAULT 0,
    FOREIGN KEY (id_animale) REFERENCES anagrafica_animali(id_animale)
);
