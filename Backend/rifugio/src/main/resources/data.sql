-- inserimento dati all'interno del database
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