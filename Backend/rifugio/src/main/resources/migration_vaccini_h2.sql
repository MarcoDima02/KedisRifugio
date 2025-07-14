-- Migrazione per risolvere il problema del campo vaccini
-- Esegui questo script manualmente nella console H2 se necessario

-- Per H2 Database, la sintassi è leggermente diversa
-- Controlla se la colonna ha già un default
SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'CARTELLA_CLINICA' AND COLUMN_NAME = 'VACCINI';

-- Se non ha un default, aggiungi il vincolo
ALTER TABLE CARTELLA_CLINICA ALTER COLUMN VACCINI SET DEFAULT 0;

-- Aggiorna i record esistenti che potrebbero avere vaccini = NULL
UPDATE CARTELLA_CLINICA SET VACCINI = 0 WHERE VACCINI IS NULL;
