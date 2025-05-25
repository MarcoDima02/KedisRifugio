# Learning By Project - Rifugio Animali

---

### Come prima cosa andiamo a decidere la struttura delle tabelle delle informazioni da gestire :


## AnagraficaAnimali

- idAnimale (PRIMARY KEY AUTO INCREMENT NOT NULL)
- specie (INT NOT NULL)
- razza (INT)
- sesso (CHAR NOT NULL)
- data_nascita (DATE)
- descrizione (TEXT)

## Donazioni 

- idDonazione (PRIMARY KEY AUTO INCREMENT NOT NULL)
- importo (FLOAT NOT NULL)
- idPersona (INT)
- dataDonazione (DATE NOT NULL)

## Adozioni

- idAdozione (PRIMARY KEY AUTO INCREMENT NOT NULL)
- data (DATE NOT NULL)
- idAnimale (INT NOT NULL)
- idPersona (INT NOT NULL)

## VisiteVeterinarie

- idVV (PRIMARY KEY AUTO INCREMENT NOT NULL)
- dataVisita (DATE NOT NULL)
- idAnimale (INT NOT NULL)

## Persone

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

