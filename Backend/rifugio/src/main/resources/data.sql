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
('Gatto');

-- Inserimento dati per razze
INSERT INTO razze (id_specie, nome) VALUES
(1, 'Labrador'),
(1, 'Bulldog'),
(1, 'Golden Retriever'),
(1, 'Pastore Tedesco'),
(1, 'Jack Russell'),
(1, 'Beagle'),
(1, 'Chihuahua'),
(1, 'Barboncino'),
(1, 'Cocker Spaniel'),
(1, 'Border Collie'),
(1, 'Bassotto'),
(1, 'Rottweiler'),
(1, 'Boxer'),
(1, 'Dalmata'),
(1, 'Maltese'),
(1, 'Shih Tzu'),
(1, 'Carlino'),
(1, 'Setter Inglese'),
(1, 'Dobermann'),
(1, 'Akita Inu'),
(2, 'Europeo'),
(2, 'Siamese'),
(2, 'Persiano'),
(2, 'Maine Coon'),
(2, 'Siberiano'),
(2, 'Certosino'),
(2, 'British Shorthair'),
(2, 'Bengala'),
(2, 'Ragdoll'),
(2, 'Norvegese delle Foreste'),
(2, 'Sacred Birman'),
(2, 'Scottish Fold'),
(2, 'Exotic Shorthair'),
(2, 'Sphynx'),
(2, 'Abissino'),
(2, 'Orientale'),
(2, 'Devon Rex'),
(2, 'Bombay'),
(2, 'Turco Van'),
(2, 'American Curl');

-- Inserimento dati per cartella_clinica
INSERT INTO cartella_clinica (sterilizzato, vaccini, microchip, sverminazione, trattamento_antiparassitario) VALUES
(TRUE, 3, '123456789', TRUE, FALSE),
(FALSE, 1, '987654321', FALSE, TRUE),
(TRUE, 2, '111222333', TRUE, TRUE),   -- ID = 3
(TRUE, 3, '444555666', TRUE, TRUE),   -- ID = 4
(FALSE, 0, '777888999', FALSE, FALSE),-- ID = 5
(TRUE, 1, '222333444', TRUE, FALSE),  -- ID = 6
(TRUE, 2, '555666777', TRUE, TRUE),   -- ID = 7
(TRUE, 1, '888999000', TRUE, FALSE); -- ID = 8


-- Inserimento dati per anagrafica_animali
INSERT INTO anagrafica_animali (nome, id_specie, id_razza, sesso, data_nascita, data_arrivo, descrizione_breve, descrizione_lunga, id_cartella_clinica, id_stato_animale) VALUES
('Fido', 1, 1, 'M', '2020-01-01', '2021-01-01', 'Cane giocherellone', 'Ama giocare con la palla', 1, 1),
('Micia', 2, 3, 'F', '2019-05-10', '2022-03-15', 'Gatta affettuosa', 'Molto dolce e coccolona', 2, 2),
('Luna', 2, 4, 'F', '2021-02-20', '2022-06-01', 'Gatta elegante', 'Ama dormire al sole e ricevere coccole', 3, 1),
('Rocky', 1, 2, 'M', '2020-11-10', '2023-01-15', 'Cane forte', 'Molto protettivo ma affettuoso con chi conosce', 4, 1),
('Milo', 1, 1, 'M', '2019-07-05', '2023-09-12', 'Cane anziano', 'Ha bisogno di attenzioni veterinarie costanti', 5, 3),
('Zoe', 2, 3, 'F', '2022-03-18', '2023-05-10', 'Gattina vivace', 'Salta ovunque e gioca con tutto', 6, 1),
('Toby', 1, 1, 'M', '2023-01-25', '2024-01-10', 'Cucciolo curioso', 'Sta imparando a socializzare con altri animali', 7, 1),
('Nina', 2, 4, 'F', '2020-08-15', '2021-12-20', 'Gatta indipendente', 'Ama stare da sola ma è affettuosa quando vuole', 8, 1);
-- Inserimento dati per utenti
INSERT INTO utenti (nome, cognome, codice_fiscale, numero, email, password, ruolo, attivo) VALUES
('Mario', 'Rossi', 'RSSMRA80A01H501U', '3331234567', 'mario.rossi@email.com', 'password1', 'ADMIN', TRUE),
('Luca', 'Bianchi', 'BNCLCU85B02F205Z', '3339876543', 'luca.bianchi@email.com', 'password2', 'USER', TRUE),
('Anna', 'Verdi', 'VRDANN90C03H501X', '3335555555', 'anna.verdi@email.com', 'password3', 'USER', TRUE),
('Giulia', 'Neri', 'NERGLI95D04F205Y', '3334444444', 'giulia.neri@email.com', 'password4', 'USER', TRUE),
('Paolo', 'Russo', 'RSSPLA85E05H501Z', '3332222222', 'paolo.russo@email.com', 'password5', 'ADMIN', TRUE),
('Marco','Dima','DMIMCN02E15C722Q','3467617419','dimamarco02@gmail.com','Md02@25w','USER', TRUE);

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
INSERT INTO adozioni (id_animale, id_persona, data_adozione, id_step_adozioni, note) VALUES
(1, 2, '2024-03-10', 1, 'Sono un appassionato di cani da sempre. Vivo in una casa con giardino e ho esperienza con animali domestici. Lavoro da casa quindi posso dedicare molto tempo al cane. Ho già avuto un Labrador in passato e conosco bene le esigenze della razza.'),
(2, 3, '2024-04-12', 2, 'Amo i gatti e sto cercando un compagno felino. Vivo da sola in un appartamento tranquillo al secondo piano. Non ho altri animali ma ho esperienza con i gatti di famiglia. Posso offrire tanto amore e attenzioni.');

-- Inserimento dati per visite_veterinarie
INSERT INTO visite_veterinarie (data, ora, id_animale, motivo, esito) VALUES
('2024-04-01', '10:00:00', 1, 'Vaccinazione', 'In attesa'),
('2024-04-15', '11:30:00', 2, 'Controllo annuale', 'In attesa');

-- Inserimento dati per immagini di prova
-- INSERT INTO immagini (nome, tipo, dati, data_caricamento, id_animale) VALUES
-- ('banner.png', 'image/png', 0xff, '2024-06-27', 1);
