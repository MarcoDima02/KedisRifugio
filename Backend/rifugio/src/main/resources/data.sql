INSERT INTO stato_animale (descrizione)
VALUES ('disponibile_per_adozione');

INSERT INTO specie (nome)
VALUES ('cane');

INSERT INTO razze (id_specie, nome)
VALUES (1, 'labrador');

INSERT INTO cartella_clinica (sterilizzato, vaccini, microchip, sverminazione, trattamento_antiparassitario)
VALUES (FALSE, 2, 'MICRO123456789', TRUE, TRUE);


-- 4. Creazione di una cartella clinica provvisoria (collegamento temporaneo)
INSERT INTO cartella_clinica (sterilizzato, vaccini, microchip, sverminazione, trattamento_antiparassitario)
VALUES (FALSE, 0, NULL, FALSE, FALSE);

-- 5. Inserimento animale
INSERT INTO anagrafica_animali (
    nome,
    id_specie,
    id_razza,
    sesso,
    data_nascita,
    data_arrivo,
    descrizione_breve,
    descrizione_lunga,
    id_cartella_clinica,
    id_stato_animale
)
VALUES (
    'Buddy',
    1,
    1,
    'M',
    '2022-05-15',
    '2024-06-20',
    'cane_giovane_e_giocherellone',
    'Cane di taglia media, molto socievole e adatto ai bambini.',
    1,
    1
);


-- INSERT INTO StepAdozioni (ColloquioPerAffido, VisitaDomiciliare, AffidoProva, Adozione, ControlliPostAffido) VALUES 
-- (TRUE, FALSE, FALSE, FALSE, FALSE),
-- (TRUE, TRUE, FALSE, FALSE, FALSE),
-- (TRUE, TRUE, TRUE, FALSE, FALSE),
-- (TRUE, TRUE, TRUE, TRUE, FALSE),
-- (TRUE, TRUE, TRUE, TRUE, TRUE);

INSERT INTO utenti (nome, cognome, codice_fiscale, numero, email, password, ruolo) VALUES
('Mario', 'Rossi', 'RSSMRA80A01H501U', '3331234567', 'mario.rossi@example.com', 'passMario1', 'USER'),
('Luca', 'Bianchi', 'BNCLCU85B12H501V', '3339876543', 'luca.bianchi@example.com', 'passLuca2', 'USER'),
('Giulia', 'Verdi', 'VRDGLI90C22H501W', '3341231234', 'giulia.verdi@example.com', 'passGiulia3', 'USER'),
('Elena', 'Neri', 'NRELNA95D31H501X', '3353213210', 'elena.neri@example.com', 'passElena4', 'USER'),
('Admin', 'Admin', 'ADMADM00E01H501Y', '3399999999', 'admin@example.com', 'adminPass5', 'ADMIN');


COMMIT;