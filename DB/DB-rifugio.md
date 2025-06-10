# Learning By Project - Rifugio Animali

---

### Come prima cosa andiamo a decidere la struttura delle tabelle delle informazioni da gestire :


## AnagraficaAnimali

- IdAnimale (PRIMARY KEY AUTO INCREMENT NOT NULL)
- Specie (INT NOT NULL)
- Razza (INT)
- Sesso (CHAR NOT NULL)
- DataNascita (DATE)
- Descrizione (TEXT)

## Donazioni 

- IdDonazione (PRIMARY KEY AUTO INCREMENT NOT NULL)
- Importo (FLOAT NOT NULL)
- IdPersona (INT)
- DataDonazione (DATE NOT NULL)
- Descrizione (VARCHAR 200)

## Adozioni

- idAdozione (PRIMARY KEY AUTO INCREMENT NOT NULL)
- idAnimale (INT NOT NULL)
- idPersona (INT NOT NULL)
- dataAdozione (DATE NOT FULL)

## VisiteVeterinarie

- idVV (PRIMARY KEY AUTO INCREMENT NOT NULL)
- dataVisita (DATE NOT NULL)
- idAnimale (INT NOT NULL)

## Utenti

- idPersona (PRIMARY KEY AUTO INCREMENT NOT NULL)
- nome (TEXT NOT NULL)
- cognome (TEXT NOT NULL)
- codiceFiscale (TEXT NOT NULL)
- numero (TEXT NOT NULL)

## Specie

- id (PRIMARY KEY AUTO INCREMENT NOT NULL)
- nome (TEXT NOT NULL)

## Razze 

- idRazza (PRIMARY KEY AUTO INCREMENT NOT NULL)
- idSpecie (INT NOT NULL)
- nome (TEXT NOT NULL)

