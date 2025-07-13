-- Script per aggiornare la tabella immagini esistente
-- Aggiunge le colonne per immagine principale e ordinamento

-- Aggiungi le nuove colonne se non esistono già
ALTER TABLE immagini 
ADD COLUMN IF NOT EXISTS is_principale BOOLEAN DEFAULT FALSE;

ALTER TABLE immagini 
ADD COLUMN IF NOT EXISTS ordine_visualizzazione INT DEFAULT 0;

-- Aggiorna i valori di ordinamento per le immagini esistenti
UPDATE immagini 
SET ordine_visualizzazione = id_immagine 
WHERE ordine_visualizzazione = 0;

-- Per ogni animale, imposta la prima immagine come principale se non ce n'è già una
UPDATE immagini 
SET is_principale = TRUE 
WHERE id_immagine IN (
    SELECT MIN(id_immagine) 
    FROM immagini 
    WHERE id_animale IS NOT NULL 
    GROUP BY id_animale
) 
AND NOT EXISTS (
    SELECT 1 
    FROM immagini i2 
    WHERE i2.id_animale = immagini.id_animale 
    AND i2.is_principale = TRUE
);
