-- Inserimento dati per stato_animale
INSERT INTO stato_animale (descrizione) VALUES
('Disponibile'),
('Adottato'),
('In cura'),
('In affido'),
('Deceduto');

-- Inserimento dati per specie
INSERT INTO specie (nome) VALUES
('Cane'),
('Gatto'),
('Coniglio'),
('Uccello'),
('Criceto');

-- Inserimento dati per razze
INSERT INTO razze (id_specie, nome) VALUES
(1, 'Labrador'),
(1, 'Bulldog'),
(2, 'Europeo'),
(2, 'Siamese'),
(3, 'Nano');

-- Inserimento dati per cartella_clinica
INSERT INTO cartella_clinica (sterilizzato, vaccini, microchip, sverminazione, trattamento_antiparassitario) VALUES
(TRUE, 3, '123456789', TRUE, FALSE),
(FALSE, 1, '987654321', FALSE, TRUE),
(TRUE, 2, '555555555', TRUE, TRUE),
(FALSE, 0, '111111111', FALSE, FALSE),
(TRUE, 4, '222222222', TRUE, TRUE);

-- Inserimento dati per anagrafica_animali
INSERT INTO anagrafica_animali (nome, id_specie, id_razza, sesso, data_nascita, data_arrivo, descrizione_breve, descrizione_lunga, id_cartella_clinica, id_stato_animale) VALUES
('Fido', 1, 1, 'M', '2020-01-01', '2021-01-01', 'Cane giocherellone', 'Ama giocare con la palla', 1, 1),
('Micia', 2, 3, 'F', '2019-05-10', '2022-03-15', 'Gatta affettuosa', 'Molto dolce e coccolona', 2, 2),
('Bunny', 3, 5, 'F', '2021-06-20', '2022-07-01', 'Coniglio vivace', 'Salta ovunque', 3, 1),
('Cip', 4, NULL, 'M', '2022-02-02', '2022-03-01', 'Uccellino canterino', 'Canta tutto il giorno', 4, 3),
('Hammy', 5, NULL, 'M', '2023-01-15', '2023-02-10', 'Criceto curioso', 'Ama correre sulla ruota', 5, 1);

-- Inserimento dati per utenti
INSERT INTO utenti (nome, cognome, codice_fiscale, numero, email, password, ruolo) VALUES
('Mario', 'Rossi', 'RSSMRA80A01H501U', '3331234567', 'mario.rossi@email.com', 'password1', 'ADMIN'),
('Luca', 'Bianchi', 'BNCLCU85B02F205Z', '3339876543', 'luca.bianchi@email.com', 'password2', 'USER'),
('Anna', 'Verdi', 'VRDANN90C03H501X', '3335555555', 'anna.verdi@email.com', 'password3', 'USER'),
('Giulia', 'Neri', 'NERGLI95D04F205Y', '3334444444', 'giulia.neri@email.com', 'password4', 'USER'),
('Paolo', 'Russo', 'RSSPLA85E05H501Z', '3332222222', 'paolo.russo@email.com', 'password5', 'ADMIN');

-- Inserimento dati per step_adozioni
INSERT INTO step_adozioni (nome_step, descrizione_step) VALUES
('Colloquio', 'Colloquio conoscitivo'),
('Visita domiciliare', 'Controllo ambiente domestico'),
('Affido prova', 'Periodo di prova'),
('Adozione', 'Adozione definitiva'),
('Controllo post-affido', 'Controllo dopo adozione');

-- Inserimento dati per donazioni
INSERT INTO donazioni (importo, id_persona, data_donazione, descrizione) VALUES
(50.00, 1, '2024-01-15', 'Donazione annuale'),
(30.00, 2, '2024-02-20', 'Donazione una tantum'),
(100.00, 3, '2024-03-10', 'Donazione speciale'),
(25.00, 4, '2024-04-05', 'Donazione mensile'),
(75.00, 5, '2024-05-12', 'Donazione per progetto');

-- Inserimento dati per adozioni
INSERT INTO adozioni (id_animale, id_persona, data_adozione, id_step_adozioni) VALUES
(1, 2, '2024-03-10', 1),
(2, 3, '2024-04-12', 2),
(3, 4, '2024-05-20', 3),
(4, 5, '2024-06-15', 4),
(5, 1, '2024-07-01', 5);

-- Inserimento dati per visite_veterinarie
INSERT INTO visite_veterinarie (data, ora, id_animale, motivo, esito) VALUES
('2024-04-01', '10:00:00', 1, 'Vaccinazione', 'Tutto ok'),
('2024-04-15', '11:30:00', 2, 'Controllo annuale', 'In salute'),
('2024-05-10', '09:00:00', 3, 'Visita di controllo', 'Guarito'),
('2024-06-05', '15:00:00', 4, 'Problema respiratorio', 'In cura'),
('2024-07-20', '08:30:00', 5, 'Controllo peso', 'Normale');
