-- Script di migrazione per aggiungere il campo 'attivo' alla tabella utenti
-- Eseguire questo script su database esistenti per implementare il soft delete

-- Aggiunge la colonna attivo se non esiste già
ALTER TABLE utenti 
ADD COLUMN IF NOT EXISTS attivo BOOLEAN DEFAULT TRUE NOT NULL;

-- Imposta tutti gli utenti esistenti come attivi
UPDATE utenti SET attivo = TRUE WHERE attivo IS NULL;

-- Verifica che tutti gli utenti siano attivi
-- SELECT COUNT(*) as utenti_attivi FROM utenti WHERE attivo = TRUE;
-- SELECT COUNT(*) as utenti_totali FROM utenti;
