package com.rifugio.rifugio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.Adozioni;
import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.entities.Stato_Animale;
import com.rifugio.rifugio.repos.AdozioniRepo;
import com.rifugio.rifugio.repos.AnagraficaAnimaliRepo;
import com.rifugio.rifugio.repos.StatoAnimaleRepo;

@Service
public class AdozioniServiceImpl implements AdozioniService {

    @Autowired
    private AdozioniRepo adozioniRepo;
    
    @Autowired
    private AnagraficaAnimaliRepo anagraficaAnimaliRepo;
    
    @Autowired
    private StatoAnimaleRepo statoAnimaleRepo;

    @Override
    public List<Adozioni> getAllAdozioni() {
        return adozioniRepo.findAll();
    }

    @Override
    public Adozioni getAdozioneById(Integer id) {
        return adozioniRepo.findById(id).orElse(null);
    }

    @Override
    public Adozioni createAdozione(Adozioni adozione) {
        // Controlla se lo step è "Adozione" (ID=4) per aggiornare automaticamente lo stato dell'animale
        if (adozione.getStepAdozione() != null && adozione.getStepAdozione().getId_step_adozioni() == 4) {
            aggiornaStatoAnimaleAdottato(adozione.getAnimale().getIdAnimale());
        }
        
        return adozioniRepo.save(adozione);
    }

    @Override
    public Adozioni updateAdozione(Integer id, Adozioni adozione) {
        Optional<Adozioni> existing = adozioniRepo.findById(id);
        if (existing.isPresent()) {
            Adozioni aggiornata = existing.get();
            aggiornata.setAnimale(adozione.getAnimale());
            aggiornata.setPersona(adozione.getPersona());
            aggiornata.setDataAdozione(adozione.getDataAdozione());
            aggiornata.setStepAdozione(adozione.getStepAdozione());
            aggiornata.setNote(adozione.getNote());
            
            // Controlla se lo step è "Adozione" (ID=4) per aggiornare automaticamente lo stato dell'animale
            if (adozione.getStepAdozione() != null) {
                if (adozione.getStepAdozione().getId_step_adozioni() == 4) {
                    // Se lo step è "Adozione", imposta l'animale come "Adottato"
                    aggiornaStatoAnimaleAdottato(aggiornata.getAnimale().getIdAnimale());
                } else {
                    // Se lo step non è "Adozione" ma l'animale era precedentemente adottato, 
                    // potremmo volerlo rimettere come "Disponibile" (ID=1)
                    // Questo avviene solo se il vecchio step era "Adozione"
                    if (existing.get().getStepAdozione() != null && 
                        existing.get().getStepAdozione().getId_step_adozioni() == 4) {
                        aggiornaStatoAnimaleDisponibile(aggiornata.getAnimale().getIdAnimale());
                    }
                }
            }
            
            return adozioniRepo.save(aggiornata);
        }
        return null;
    }

    public List<Adozioni> getUltimeAdozioni() {
        return adozioniRepo.findUltimeAdozioni(PageRequest.of(0, 5));
    }
    
    /**
     * Metodo helper per aggiornare lo stato dell'animale a "Adottato" quando l'adozione è completata
     */
    private void aggiornaStatoAnimaleAdottato(Integer idAnimale) {
        try {
            // Recupera l'animale
            Optional<AnagraficaAnimali> animaleOpt = anagraficaAnimaliRepo.findById(idAnimale);
            if (animaleOpt.isPresent()) {
                AnagraficaAnimali animale = animaleOpt.get();
                
                // Recupera lo stato "Adottato" (ID=2)
                Optional<Stato_Animale> statoAdottatoOpt = statoAnimaleRepo.findById(2);
                if (statoAdottatoOpt.isPresent()) {
                    animale.setStatoAnimale(statoAdottatoOpt.get());
                    anagraficaAnimaliRepo.save(animale);
                    System.out.println("Stato animale aggiornato automaticamente a 'Adottato' per l'animale ID: " + idAnimale);
                }
            }
        } catch (Exception e) {
            System.err.println("Errore durante l'aggiornamento dello stato dell'animale: " + e.getMessage());
            // Non rilanciamo l'eccezione per non bloccare il salvataggio dell'adozione
        }
    }
    
    /**
     * Metodo helper per aggiornare lo stato dell'animale a "Disponibile" quando l'adozione viene annullata
     */
    private void aggiornaStatoAnimaleDisponibile(Integer idAnimale) {
        try {
            // Recupera l'animale
            Optional<AnagraficaAnimali> animaleOpt = anagraficaAnimaliRepo.findById(idAnimale);
            if (animaleOpt.isPresent()) {
                AnagraficaAnimali animale = animaleOpt.get();
                
                // Recupera lo stato "Disponibile" (ID=1)
                Optional<Stato_Animale> statoDisponibileOpt = statoAnimaleRepo.findById(1);
                if (statoDisponibileOpt.isPresent()) {
                    animale.setStatoAnimale(statoDisponibileOpt.get());
                    anagraficaAnimaliRepo.save(animale);
                    System.out.println("Stato animale aggiornato automaticamente a 'Disponibile' per l'animale ID: " + idAnimale);
                }
            }
        } catch (Exception e) {
            System.err.println("Errore durante l'aggiornamento dello stato dell'animale: " + e.getMessage());
            // Non rilanciamo l'eccezione per non bloccare il salvataggio dell'adozione
        }
    }
}
