# Guida al flusso di lavoro con Git e GitHub

## 1. Creazione della branch personale (già create)
Se non hai ancora una branch personale, creala partendo da `main`:

```bash
git checkout main
git pull origin main
git checkout -b [cognome]  # Comando essenziale per cambiare branch
git push -u origin [cognome]
```

## 2. Fare commit del proprio lavoro
Lavora sul tuo codice e usa commit chiari e descrittivi:
una volta che si decide di committare, esegui i seguenti comandi:

```bash
git add .     # il punto fa si che vengano aggiunti in area di stage tutti i file che sono stati modificati
git commit -m "Aggiunto controller per gestione prodotti"
git push origin [cognome]
```

*Ribadisco:fai commit frequenti e con messaggi significativi!*

## 3. Aggiornare la branch con le modifiche del main (opzionale ma consigliato)
Se il `main` viene aggiornato, puoi integrare le novità nella tua branch:
Ogni volta che il main verrà modificato, tuttavia, tutti i membri verranno avvisati, ma è buona usanza effettuare il pull dal main prima di spostarsi nella propria branch per continuare il proprio lavoro.

```bash
git checkout main
git pull origin main
git checkout [cognome]
git merge main OPPURE git rebase main (più pulito ma richiede attenzione)  #questo comando permette di integrare le modifiche dal main, all'interno della propria branch
```

## 4. Completamento del lavoro e merge nel main
Una volta terminata la tua parte e testato il codice:

1. Vai su GitHub  
2. Apri una **Pull Request** dalla tua branch (cognome) verso `main`  
3. Dai un titolo chiaro e descrivi le modifiche fatte  
4. Aspetta la revisione o approvazione  
5. Una volta approvata, fai il **merge nel main**  

