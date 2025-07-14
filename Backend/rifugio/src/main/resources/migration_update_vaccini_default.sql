-- Aggiorna la tabella cartella_clinica per aggiungere default al campo vaccini
ALTER TABLE cartella_clinica ALTER COLUMN vaccini SET DEFAULT 0;

-- Aggiorna i record esistenti che potrebbero avere vaccini = NULL
UPDATE cartella_clinica SET vaccini = 0 WHERE vaccini IS NULL;
