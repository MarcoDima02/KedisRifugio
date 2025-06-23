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

-- Esempio di inserimento utente
-- INSERT INTO Utenti (Nome, Cognome, CodiceFiscale, Numero, Email) VALUES 
-- ('Mario', 'Rossi', 'RSSMRA80A01H501Z', '3331234567', 'mario.rossi@email.com'),
-- ('Anna', 'Verdi', 'VRDNNA85B15H501W', '3337654321', 'anna.verdi@email.com');

COMMIT;