# Learning By Project - Rifugio Animali

---

## Struttura del Database

### AnagraficaAnimali
- **IdAnimale**: INT PRIMARY KEY AUTO_INCREMENT NOT NULL
- **Nome**: VARCHAR(45) NOT NULL
- **IdSpecie**: INT NOT NULL FOREIGN KEY (Specie.Id)
- **IdRazza**: INT NULL FOREIGN KEY (Razze.IdRazza)
- **Sesso**: CHAR(1) NOT NULL (M/F)
- **DataNascita**: DATE NOT NULL
- **DataArrivo**: DATE NOT NULL
- **DescrizioneBreve**: VARCHAR(100)
- **DescrizioneLunga**: VARCHAR(400)
- **idCartellaClinica** : INT NOT NULL
- **Stato**: INT NOT NULL FOREIGN KEY (StatoAnimale.IdStato)

---

### CartellaClinica
- **IdCartellaClinica**: INT PRIMARY KEY AUTO_INCREMENT NOT NULL
- **IdAnimale**: INT NOT NULL FOREIGN KEY (AnagraficaAnimali.IdAnimale)
- **Sterilizzato**: BOOLEAN DEFAULT FALSE
- **Vaccini**: INT NOT NULL
- **Microchip**: VARCHAR(50)
- **Sverminazione**: BOOLEAN DEFAULT FALSE
- **TrattamentoAntiparassitario**: BOOLEAN DEFAULT FALSE

---

### StatoAnimale
- **IdStatoAnimale**: INT PRIMARY KEY AUTO_INCREMENT NOT NULL
- **Descrizione**: VARCHAR(50) NOT NULL  
*(Esempi: In adozione, Adottato, Non disponibile)*

---

### StepAdozioni
- **IdStepAdozioni**: INT PRIMARY KEY AUTO_INCREMENT NOT NULL
- **ColloquioPerAffido**: BOOLEAN DEFAULT FALSE
- **VisitaDomiciliare**: BOOLEAN DEFAULT FALSE
- **AffidoProva**: BOOLEAN DEFAULT FALSE
- **Adozione**: BOOLEAN DEFAULT FALSE
- **ControlliPostAffido**: BOOLEAN DEFAULT FALSE

---

### Donazioni
- **IdDonazione**: INT PRIMARY KEY AUTO_INCREMENT NOT NULL
- **Importo**: DECIMAL(10,2) NOT NULL
- **IdPersona**: INT NOT NULL FOREIGN KEY (Utenti.IdPersona)
- **DataDonazione**: DATE NOT NULL
- **Descrizione**: VARCHAR(150)

---

### Adozioni
- **IdAdozione**: INT PRIMARY KEY AUTO_INCREMENT NOT NULL
- **IdAnimale**: INT NOT NULL FOREIGN KEY (AnagraficaAnimali.IdAnimale)
- **IdPersona**: INT NOT NULL FOREIGN KEY (Utenti.IdPersona)
- **DataAdozione**: DATE NOT NULL
- **IdStepAdozioni**: INT NULL FOREIGN KEY (StepAdozioni.IdStepAdozioni)

---

### VisiteVeterinarie
- **IdVisita**: INT PRIMARY KEY AUTO_INCREMENT NOT NULL
- **Data**: DATE NOT NULL
- **Ora**: TIME NOT NULL
- **IdAnimale**: INT NOT NULL FOREIGN KEY (AnagraficaAnimali.IdAnimale)
- **Motivo**: VARCHAR(150) NOT NULL
- **Esito**: VARCHAR(150) NOT NULL

---

### Utenti
- **IdPersona**: INT PRIMARY KEY AUTO_INCREMENT NOT NULL
- **Nome**: VARCHAR(50) NOT NULL
- **Cognome**: VARCHAR(50) NOT NULL
- **CodiceFiscale**: VARCHAR(16) NOT NULL UNIQUE
- **Numero**: VARCHAR(50)
- **Email**: VARCHAR(50)

---

### Specie
- **Id**: INT PRIMARY KEY AUTO_INCREMENT NOT NULL
- **Nome**: VARCHAR(50) NOT NULL

---

### Razze
- **IdRazza**: INT PRIMARY KEY AUTO_INCREMENT NOT NULL
- **IdSpecie**: INT NOT NULL FOREIGN KEY (Specie.Id)
- **Nome**: VARCHAR(50) NOT NULL

---
