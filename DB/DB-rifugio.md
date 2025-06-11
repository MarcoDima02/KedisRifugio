# Learning By Project - Rifugio Animali

---

### Come prima cosa andiamo a decidere la struttura delle tabelle delle informazioni da gestire :


## AnagraficaAnimali

- IdAnimale (PRIMARY KEY AUTO INCREMENT NOT NULL)
- IdSpecie (INT NOT NULL FOREIGN KEY)
- IdRazza (INT FOREIGN KEY) 
- Sesso (CHAR NOT NULL) 
- DataNascita (DATE NOT NULL)
- DataArrivo (DATE NOT NULL)
- DescrizioneBreve (VARCHAR 100)
- DescrizioneLunga (VARCHAR 400)
- Vaccinato (BOOL)
- Sterilizzato (BOOL)
- Microchip (BOOL)
- Stato (INT NOT NULL) --> Se è adottato, in adozione o non adottato

## Stati

- IdStato (PRIMARY KEY AUTO INCREMENT NOT NULL)
- Descrizione (VARCHAR 30 NOT NULL)

## Donazioni 

- IdDonazione (PRIMARY KEY AUTO INCREMENT NOT NULL)
- Importo (DECIMAL(10,2) NOT NULL)
- IdPersona (INT FOREIGN KEY NOT NULL)
- DataDonazione (DATE NOT NULL)
- Descrizione (VARCHAR 150)

## Adozioni

- idAdozione (PRIMARY KEY AUTO INCREMENT NOT NULL)
- idAnimale (INT NOT NULL FOREIGN KEY)
- idPersona (INT NOT NULL FOREIGN KEY)
- dataAdozione (DATE NOT NULL)

## VisiteVeterinarie

- idVisita (PRIMARY KEY AUTO INCREMENT NOT NULL)
- Data (DATE NOT NULL)
- Ora (TIME NOT NULL)
- idAnimale (INT NOT NULL FOREIGN KEY)
- Motivo (VARCHAR 150 NOT NULL)
- Esito (VARCHAR 150 NOT NULL)

## Utenti

- IdPersona (PRIMARY KEY AUTO INCREMENT NOT NULL)
- Nome (VARCHAR 50 NOT NULL)
- Cognome (VARCHAR 50 NOT NULL)
- CodiceFiscale (TEXT NOT NULL)
- Numero (VARCHAR 50 NOT NULL)
- Email (VARCHAR 50 NOT NULL)

## Specie

- Id (PRIMARY KEY AUTO INCREMENT NOT NULL)
- Nome (VARCHAR 50 NOT NULL)

## Razze 

- IdRazza (PRIMARY KEY AUTO INCREMENT NOT NULL)
- IdSpecie (INT NOT NULL FOREIGN KEY)
- Nome (TEXT NOT NULL)

