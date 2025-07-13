# Implementazione Soft Delete per Utenti

## Panoramica
Questa implementazione introduce il **soft delete** per la gestione degli utenti nel sistema rifugio. Invece di eliminare fisicamente i record dal database, gli utenti vengono contrassegnati come "non attivi" preservando l'integrità referenziale.

## Motivazione
Il soft delete risolve il problema dell'eliminazione di utenti che hanno:
- Adozioni registrate
- Donazioni effettuate
- Altri dati collegati nel sistema

Eliminare fisicamente un utente causerebbe violazioni di foreign key e perdita di dati storici importanti.

## Come Funziona

### 1. Campo `attivo` 
- **Tipo**: BOOLEAN
- **Default**: TRUE
- **Significato**:
  - `TRUE` = Utente attivo e funzionale
  - `FALSE` = Utente eliminato logicamente (soft delete)

### 2. Metodi del Service
- `getAllUtentiAttivi()` - Solo utenti attivi (per operazioni normali)
- `getAllUtenti()` - Tutti gli utenti (inclusi eliminati, per admin)
- `deleteUtente(id)` - Soft delete (imposta attivo = FALSE)
- `ripristinaUtente(id)` - Ripristina utente eliminato
- `eliminaUtenteFisicamente(id)` - Hard delete (solo admin, casi estremi)

### 3. Comportamenti del Sistema

#### Login
- Solo utenti attivi possono effettuare il login
- Utenti eliminati logicamente non possono accedere

#### Form di Creazione
- Nuove adozioni/donazioni mostrano solo utenti attivi
- Previene assegnazione a utenti eliminati

#### Validazione Duplicati
- Email/Codice Fiscale: verificati solo tra utenti attivi
- Permette riutilizzo dati di utenti eliminati

#### Dashboard Admin
- **Vista Default**: Solo utenti attivi
- **Vista Estesa**: Tutti inclusi eliminati (toggle)
- **Azioni**:
  - Utenti attivi: Modifica, Visualizza, Elimina (soft)
  - Utenti eliminati: Visualizza, Ripristina, Elimina definitivamente

## Vantaggi

### ✅ Integrità dei Dati
- Nessuna perdita di dati storici
- Adozioni e donazioni mantengono riferimenti validi
- Report e statistiche rimangono accurate

### ✅ Tracciabilità
- Cronologia completa delle azioni
- Possibilità di audit trail
- Recupero di utenti eliminati per errore

### ✅ Sicurezza
- Eliminazione accidentale reversibile
- Processo a due fasi per eliminazione definitiva
- Controlli di autorizzazione

### ✅ Conformità
- Rispetta normative sulla conservazione dati
- Facilita gestione GDPR (se necessario)

## Migrazione Database

### Per Database Esistenti
```sql
-- Eseguire il file migration_add_soft_delete.sql
ALTER TABLE utenti 
ADD COLUMN IF NOT EXISTS attivo BOOLEAN DEFAULT TRUE NOT NULL;

UPDATE utenti SET attivo = TRUE WHERE attivo IS NULL;
```

### Per Nuove Installazioni
Il campo `attivo` è già incluso in `schema.sql` e `data.sql`.

## Esempi di Utilizzo

### Scenario 1: Utente con Adozioni
```java
// Prima: Errore di foreign key
utentiService.deleteUtente(123); // ERRORE!

// Dopo: Soft delete funziona
utentiService.deleteUtente(123); // ✅ Utente disattivato
// Le adozioni rimangono collegate ma l'utente non può più accedere
```

### Scenario 2: Ripristino Utente
```java
// Admin può ripristinare utenti eliminati per errore
utentiService.ripristinaUtente(123); // ✅ Utente riattivato
```

### Scenario 3: Eliminazione Definitiva
```java
// Solo dopo soft delete, admin può eliminare fisicamente
utentiService.deleteUtente(123);           // Soft delete
utentiService.eliminaUtenteFisicamente(123); // Hard delete
```

## Best Practices

1. **Usare sempre soft delete** come prima opzione
2. **Hard delete solo in casi estremi** (spam, test, ecc.)
3. **Verificare dipendenze** prima dell'eliminazione fisica
4. **Documentare motivi** per eliminazioni definitive
5. **Backup regolari** prima di operazioni di pulizia

## Considerazioni Future

### Pulizia Automatica
Considerare script di pulizia per eliminare fisicamente utenti soft-deleted dopo X anni:

```sql
-- Esempio: elimina utenti soft-deleted da più di 7 anni
DELETE FROM utenti 
WHERE attivo = FALSE 
AND updated_at < DATE_SUB(NOW(), INTERVAL 7 YEAR);
```

### Audit Trail
Aggiungere campi di audit per tracciare:
- `deleted_at` - Timestamp eliminazione
- `deleted_by` - Chi ha eliminato l'utente
- `delete_reason` - Motivo eliminazione

### Performance
Per grandi quantità di dati, considerare indici:
```sql
CREATE INDEX idx_utenti_attivo ON utenti(attivo);
```
