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
(TRUE, 3, '380260001234567', TRUE, TRUE),   -- ID = 1 (Fido)
(TRUE, 2, '380260002345678', TRUE, FALSE),  -- ID = 2 (Luna)
(FALSE, 1, '380260003456789', FALSE, TRUE), -- ID = 3 (Buddy)
(TRUE, 4, '380260004567890', TRUE, TRUE),   -- ID = 4 (Mia)
(TRUE, 2, '380260005678901', TRUE, FALSE),  -- ID = 5 (Charlie)
(FALSE, 0, '', FALSE, FALSE),               -- ID = 6 (Bella - cucciola senza microchip)
(TRUE, 3, '380260006789012', TRUE, TRUE),   -- ID = 7 (Oliver)
(TRUE, 1, '380260007890123', TRUE, FALSE),  -- ID = 8 (Sophie)
(FALSE, 1, '380260008901234', FALSE, TRUE), -- ID = 9 (Max)
(TRUE, 2, '380260009012345', TRUE, TRUE);   -- ID = 10 (Coco)

-- Inserimento dati per anagrafica_animali
INSERT INTO anagrafica_animali (nome, id_specie, id_razza, sesso, data_nascita, data_arrivo, descrizione_breve, descrizione_lunga, id_cartella_clinica, id_stato_animale) VALUES
('Fido', 1, 1, 'M', '2022-03-15', '2025-06-02', 'Labrador giocherellone e socievole', 'Fido è un Labrador di 3 anni molto affettuoso e ben educato. Ama giocare con la palla, fare lunghe passeggiate e socializzare con altri cani. È perfetto per famiglie con bambini. Ha completato il ciclo di vaccinazioni ed è sterilizzato.', 1, 1),
('Luna', 2, 21, 'F', '2023-01-10', '2025-06-05', 'Gatta europea dolce e tranquilla', 'Luna è una bellissima gatta europea di 2 anni dal carattere dolce e affettuoso. Ama le coccole e dormire al sole. È molto pulita e indipendente, perfetta per chi cerca un compagno felino calmo e amorevole.', 2, 1),
('Buddy', 1, 4, 'M', '2024-08-20', '2025-06-10', 'Pastore Tedesco giovane e intelligente', 'Buddy è un Pastore Tedesco di 11 mesi molto intelligente e vivace. Sta ancora imparando i comandi base ma è molto promettente. Ideale per chi ha esperienza con cani di taglia grande e cerca un compagno fedele da educare.', 3, 1),
('Mia', 2, 23, 'F', '2021-05-20', '2025-06-15', 'Persiana elegante e riservata', 'Mia è una splendida Persiana di 4 anni dal carattere elegante e un po'' riservato. Una volta che si abitua alle persone diventa molto affettuosa. Ha il pelo lungo che richiede cure quotidiane. Perfetta per chi ama i gatti di razza.', 4, 1),
('Charlie', 1, 6, 'M', '2020-11-12', '2025-06-18', 'Beagle anziano ma energico', 'Charlie è un Beagle di 4 anni e mezzo ancora molto energico nonostante l''età. Ama esplorare e seguire le tracce olfattive. È molto socievole con persone e altri cani. Cerca una famiglia che gli dedichi tempo per le passeggiate.', 5, 1),
('Bella', 1, 7, 'F', '2025-03-05', '2025-06-22', 'Cucciola di Chihuahua vivacissima', 'Bella è una piccola Chihuahua di 4 mesi piena di energia e curiosità. È ancora in fase di socializzazione e addestramento. Nonostante la piccola taglia ha un carattere coraggioso. Perfetta per chi vuole crescere un cucciolo dall''inizio.', 6, 1),
('Oliver', 2, 22, 'M', '2022-07-08', '2025-06-25', 'Siamese chiacchierone e affettuoso', 'Oliver è un magnifico Siamese di 3 anni dal carattere vivace e comunicativo. Ama "parlare" con i suoi umani e seguirli per casa. È molto intelligente e giocherellone. Ideale per chi cerca un gatto interattivo e socievole.', 7, 1),
('Sophie', 2, 24, 'F', '2023-09-15', '2025-06-28', 'Maine Coon giovane e maestosa', 'Sophie è una splendida Maine Coon di 1 anno e 10 mesi dal carattere dolce e maestoso. Nonostante la taglia imponente è molto gentile e paziente. Ama giocare e ricevere attenzioni. Il suo pelo richiede spazzolature regolari.', 8, 1),
('Max', 1, 3, 'M', '2024-12-01', '2025-07-01', 'Cucciolo di Golden Retriever adorabile', 'Max è un Golden Retriever di 7 mesi dolcissimo e pieno di vita. Sta imparando i comandi base e la pulizia in casa. Ama giocare e ricevere coccole. Sarà un cane di taglia grande, ideale per famiglie attive con spazio adeguato.', 9, 1),
('Coco', 2, 25, 'F', '2021-12-20', '2025-07-03', 'Siberiana dal carattere forte', 'Coco è una Siberiana di 3 anni e mezzo dal carattere indipendente ma affettuoso. È molto attiva e ama arrampicarsi. Ha bisogno di spazio e stimoli mentali. Il suo pelo folto richiede cure regolari, soprattutto durante la muta.', 10, 2);

-- ...existing utenti data...
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

-- Inserimento dati per donazioni (periodo giugno-luglio 2025)
INSERT INTO donazioni (importo, id_persona, data_donazione, descrizione) VALUES
(75.50, 1, '2025-06-05', 'Donazione per le cure veterinarie'),
(120.00, 2, '2025-06-12', 'Donazione mensile'),
(50.00, 3, '2025-06-20', 'Donazione per il cibo'),
(200.00, 4, '2025-06-28', 'Donazione speciale per l''estate'),
(35.75, 5, '2025-07-03', 'Donazione spontanea'),
(90.00, 6, '2025-07-08', 'Donazione per i cuccioli'),
(150.00, 1, '2025-07-12', 'Seconda donazione');

-- Inserimento dati per adozioni (periodo giugno-luglio 2025)
INSERT INTO adozioni (id_animale, id_persona, data_adozione, id_step_adozioni, note) VALUES
(1, 2, '2025-06-08', 4, 'Adozione completata con successo. Fido si è ambientato perfettamente nella nuova famiglia. I proprietari hanno un giardino grande e molta esperienza con i Labrador. Controllo post-adozione programmato per agosto.'),
(2, 3, '2025-06-15', 4, 'Luna ha trovato la sua famiglia ideale. La proprietaria vive da sola e può dedicarle tutto il tempo necessario. L''ambiente domestico è tranquillo e sicuro. Adozione definitiva completata.'),
(4, 4, '2025-06-25', 3, 'Mia è in periodo di affido prova. La famiglia sta imparando a gestire le cure del pelo lungo. Finora tutto procede bene. Prossimo controllo previsto a fine luglio per l''adozione definitiva.'),
(7, 5, '2025-07-02', 2, 'Oliver ha superato il colloquio conoscitivo. È programmata la visita domiciliare per metà luglio. Il futuro proprietario sembra molto preparato e motivato.'),
(10, 6, '2025-07-10', 1, 'Primo colloquio per Coco. La famiglia è interessata ma deve ancora decidere. Hanno richiesto più informazioni sulle cure del pelo siberiano.');

-- Aggiorno gli stati degli animali adottati
UPDATE anagrafica_animali SET id_stato_animale = 2 WHERE id_animale IN (1, 2);  -- Adottati
UPDATE anagrafica_animali SET id_stato_animale = 4 WHERE id_animale = 4;        -- In affido
UPDATE anagrafica_animali SET id_stato_animale = 1 WHERE id_animale IN (3, 5, 6, 7, 8, 9, 10); -- Disponibili

-- Inserimento dati per visite_veterinarie (passate e future)
INSERT INTO visite_veterinarie (data, ora, id_animale, motivo, esito) VALUES
-- Visite già effettuate (giugno 2025)
('2025-06-03', '09:30:00', 1, 'Controllo pre-adozione', 'Effettuata'),
('2025-06-10', '14:00:00', 2, 'Vaccinazione richiamo', 'Effettuata'),
('2025-06-15', '10:30:00', 3, 'Controllo generale cucciolo', 'Effettuata'),
('2025-06-22', '16:15:00', 4, 'Toelettatura e controllo pelo', 'Effettuata'),
('2025-06-28', '11:45:00', 5, 'Controllo articolazioni senior', 'Effettuata'),
('2025-07-05', '09:00:00', 6, 'Prima visita cucciolo', 'Effettuata'),
('2025-07-08', '15:30:00', 7, 'Controllo comportamentale', 'Effettuata'),
('2025-07-10', '13:20:00', 8, 'Spazzolatura professionale', 'Effettuata'),

-- Visite programmate (future - luglio 2025)
('2025-07-16', '10:00:00', 9, 'Seconda vaccinazione cucciolo', 'In attesa'),
('2025-07-18', '14:30:00', 3, 'Controllo crescita e sviluppo', 'In attesa'),
('2025-07-22', '09:45:00', 5, 'Controllo follow-up senior', 'In attesa'),
('2025-07-25', '11:15:00', 6, 'Seconda visita e microchip', 'In attesa'),
('2025-07-29', '16:00:00', 7, 'Controllo pre-adozione', 'In attesa'),
('2025-07-31', '10:30:00', 8, 'Controllo generale', 'In attesa'),

-- Visite agosto (più avanti nel futuro)
('2025-08-05', '09:00:00', 9, 'Terza vaccinazione cucciolo', 'In attesa'),
('2025-08-12', '14:00:00', 1, 'Controllo post-adozione', 'In attesa'),
('2025-08-15', '11:30:00', 2, 'Controllo post-adozione', 'In attesa');

-- Inserimento dati per immagini di prova (opzionale)
-- INSERT INTO immagini (nome, tipo, dati, data_caricamento, id_animale) VALUES
-- ('fido_1.jpg', 'image/jpeg', 0xff, '2025-06-02', 1),
-- ('luna_1.jpg', 'image/jpeg', 0xff, '2025-06-05', 2);
