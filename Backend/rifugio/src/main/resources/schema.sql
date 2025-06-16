-- Database Rifugio Animali - MySQL
-- Creazione del database
CREATE DATABASE IF NOT EXISTS rifugio_animali;
USE rifugio_animali;

-- Eliminazione tabelle esistenti (in ordine inverso per rispettare le foreign key)
DROP TABLE IF EXISTS VisiteVeterinarie;
DROP TABLE IF EXISTS Adozioni;
DROP TABLE IF EXISTS Donazioni;
DROP TABLE IF EXISTS AnagraficaAnimali;
DROP TABLE IF EXISTS CartellaClinica;
DROP TABLE IF EXISTS Razze;
DROP TABLE IF EXISTS Specie;
DROP TABLE IF EXISTS StatoAnimale;
DROP TABLE IF EXISTS StepAdozioni;
DROP TABLE IF EXISTS Utenti;

-- Creazione tabelle indipendenti (senza foreign key)
CREATE TABLE Utenti (
    IdPersona INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Nome VARCHAR(50) NOT NULL,
    Cognome VARCHAR(50) NOT NULL,
    CodiceFiscale VARCHAR(16) NOT NULL UNIQUE,
    Numero VARCHAR(50),
    Email VARCHAR(50)
);

CREATE TABLE StatoAnimale (
    IdStato INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Descrizione VARCHAR(50) NOT NULL
);

CREATE TABLE StepAdozioni (
    IdStepAdozioni INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    ColloquioPerAffido BOOLEAN DEFAULT FALSE,
    VisitaDomiciliare BOOLEAN DEFAULT FALSE,
    AffidoProva BOOLEAN DEFAULT FALSE,
    Adozione BOOLEAN DEFAULT FALSE,
    ControlliPostAffido BOOLEAN DEFAULT FALSE
);

CREATE TABLE Specie (
    Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Nome VARCHAR(50) NOT NULL
);

-- Creazione tabelle con foreign key di primo livello
CREATE TABLE Razze (
    IdRazza INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    IdSpecie INT NOT NULL,
    Nome VARCHAR(50) NOT NULL,
    FOREIGN KEY (IdSpecie) REFERENCES Specie(Id)
);

CREATE TABLE Donazioni (
    IdDonazione INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Importo DECIMAL(10,2) NOT NULL,
    IdPersona INT NOT NULL,
    DataDonazione DATE NOT NULL,
    Descrizione VARCHAR(150),
    FOREIGN KEY (IdPersona) REFERENCES Utenti(IdPersona)
);

-- Creazione tabella CartellaClinica (referenziata da AnagraficaAnimali)
CREATE TABLE CartellaClinica (
    IdCartella INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Sterilizzato BOOLEAN DEFAULT FALSE,
    Vaccini INT NOT NULL,
    Microchip VARCHAR(50),
    Sverminazione BOOLEAN DEFAULT FALSE,
    TrattamentoAntiparassitario BOOLEAN DEFAULT FALSE
);

-- Creazione tabella AnagraficaAnimali
CREATE TABLE AnagraficaAnimali (
    IdAnimale INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    IdSpecie INT NOT NULL,
    IdRazza INT NULL,
    Sesso CHAR(1) NOT NULL CHECK (Sesso IN ('M', 'F')),
    DataNascita DATE NOT NULL,
    DataArrivo DATE NOT NULL,
    DescrizioneBreve VARCHAR(100),
    DescrizioneLunga VARCHAR(400),
    IdCartellaClinica INT NOT NULL,
    Stato INT NOT NULL,
    FOREIGN KEY (IdSpecie) REFERENCES Specie(Id),
    FOREIGN KEY (IdRazza) REFERENCES Razze(IdRazza),
    FOREIGN KEY (IdCartellaClinica) REFERENCES CartellaClinica(IdCartella),
    FOREIGN KEY (Stato) REFERENCES StatoAnimale(IdStato)
);

-- Aggiornamento CartellaClinica per aggiungere la foreign key verso AnagraficaAnimali
ALTER TABLE CartellaClinica 
ADD COLUMN IdAnimale INT NOT NULL,
ADD FOREIGN KEY (IdAnimale) REFERENCES AnagraficaAnimali(IdAnimale);

-- Creazione tabelle finali
CREATE TABLE Adozioni (
    IdAdozione INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    IdAnimale INT NOT NULL,
    IdPersona INT NOT NULL,
    DataAdozione DATE NOT NULL,
    IdStepAdozioni INT NULL,
    FOREIGN KEY (IdAnimale) REFERENCES AnagraficaAnimali(IdAnimale),
    FOREIGN KEY (IdPersona) REFERENCES Utenti(IdPersona),
    FOREIGN KEY (IdStepAdozioni) REFERENCES StepAdozioni(IdStepAdozioni)
);

CREATE TABLE VisiteVeterinarie (
    IdVisita INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Data DATE NOT NULL,
    Ora TIME NOT NULL,
    IdAnimale INT NOT NULL,
    Motivo VARCHAR(150) NOT NULL,
    Esito VARCHAR(150) NOT NULL,
    FOREIGN KEY (IdAnimale) REFERENCES AnagraficaAnimali(IdAnimale)
);

-- Inserimento dati di esempio
INSERT INTO StatoAnimale (Descrizione) VALUES 
('In adozione'),
('Adottato'),
('Non disponibile'),
('In quarantena'),
('In cura');

INSERT INTO Specie (Nome) VALUES 
('Cane'),
('Gatto'),
('Coniglio'),
('Uccello');

INSERT INTO Razze (IdSpecie, Nome) VALUES 
(1, 'Meticcio'),
(1, 'Labrador'),
(1, 'Pastore Tedesco'),
(2, 'Europeo'),
(2, 'Persiano'),
(2, 'Siamese');

INSERT INTO StepAdozioni (ColloquioPerAffido, VisitaDomiciliare, AffidoProva, Adozione, ControlliPostAffido) VALUES 
(TRUE, FALSE, FALSE, FALSE, FALSE),
(TRUE, TRUE, FALSE, FALSE, FALSE),
(TRUE, TRUE, TRUE, FALSE, FALSE),
(TRUE, TRUE, TRUE, TRUE, FALSE),
(TRUE, TRUE, TRUE, TRUE, TRUE);

-- Esempio di inserimento utente
INSERT INTO Utenti (Nome, Cognome, CodiceFiscale, Numero, Email) VALUES 
('Mario', 'Rossi', 'RSSMRA80A01H501Z', '3331234567', 'mario.rossi@email.com'),
('Anna', 'Verdi', 'VRDNNA85B15H501W', '3337654321', 'anna.verdi@email.com');

COMMIT;