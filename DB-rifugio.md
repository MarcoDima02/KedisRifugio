# Learning By Project - Rifugio Animali

---

### Come prima cosa andiamo a decidere la struttura delle tabelle delle informazioni da gestire :


## AnagraficaAnimali

- id (PRIMARY KEY AUTO INCREMENT NOT NULL)
- specie (INT NOT NULL)
- razza (INT)
- sesso (CHAR NOT NULL)
- data_nascita (DATE)
- descrizione (TEXT)

## Donazioni 

- id (PRIMARY KEY AUTO INCREMENT NOT NULL)
- importo (FLOAT NOT NULL)
- idPersona (INT)
- dataDonazione (DATE NOT NULL)

## Adozioni

- id (PRIMARY KEY AUTO INCREMENT NOT NULL)
- dataAdozione (DATE NOT NULL)
- idAnimale (INT NOT NULL)
- idPersona (INT NOT NULL)

## VisiteVeterinarie

- id (PRIMARY KEY AUTO INCREMENT NOT NULL)
- dataVisita (DATE NOT NULL)
- idAnimale (INT NOT NULL)

## Persone

- id (PRIMARY KEY AUTO INCREMENT NOT NULL)
- nome (TEXT NOT NULL)
- cognome (TEXT NOT NULL)
- codiceFiscale (TEXT NOT NULL)
- numero (TEXT NOT NULL)

## Razze 

- id (PRIMARY KEY AUTO INCREMENT NOT NULL)
- nome (TEXT NOT NULL)

## Sesso

- id (PRIMARY KEY AUTO INCREMENT NOT NULL)
- nome (TEXT NOT NULL)

## Specie

- id (PRIMARY KEY AUTO INCREMENT NOT NULL)
- nome (TEXT NOT NULL)

## Razze 

- id (PRIMARY KEY AUTO INCREMENT NOT NULL)
- specie (INT NOT NULL)
- nome (TEXT NOT NULL)

