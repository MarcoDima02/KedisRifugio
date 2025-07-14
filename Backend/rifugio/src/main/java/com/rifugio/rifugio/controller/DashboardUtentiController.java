package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rifugio.rifugio.entities.Adozioni;
import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.entities.Utenti;
import com.rifugio.rifugio.services.AdozioniService;
import com.rifugio.rifugio.services.AnagraficaAnimaliService;
import com.rifugio.rifugio.services.DonazioniService;
import com.rifugio.rifugio.services.StepAdozioniService;
import com.rifugio.rifugio.services.UtentiService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/area-utente")
public class DashboardUtentiController {

    @Autowired
    private UtentiService utentiService;
    
    @Autowired
    private AdozioniService adozioniService;
    
    @Autowired
    private AnagraficaAnimaliService anagraficaAnimaliService;
    
    @Autowired
    private StepAdozioniService stepAdozioniService;

    @Autowired
    private DonazioniService donazioniService;

    // Area utente principale
    @GetMapping
    public String areaUtente() {
        return "area_utente";
    }

    // Visualizza i dettagli dell'utente corrente
    @GetMapping("/profilo")
    public String visualizzaProfilo(Model model, HttpSession session) {
        Utenti utente = (Utenti) session.getAttribute("user");
        if (utente == null) {
            return "redirect:/utenti/login";
        }
        model.addAttribute("utente", utente);
        return "profilo_utente";
    }

    // Mostra form di modifica per l'utente corrente
    @GetMapping("/profilo/modifica")
    public String mostraFormModificaProfilo(Model model, HttpSession session) {
        Utenti utente = (Utenti) session.getAttribute("user");
        if (utente == null) {
            return "redirect:/utenti/login";
        }
        
        // Ricarica i dati più recenti dal database
        Utenti utenteAggiornato = utentiService.getUtenteById(utente.getId_persona());
        if (utenteAggiornato != null) {
            model.addAttribute("utente", utenteAggiornato);
        } else {
            model.addAttribute("utente", utente);
        }
        
        return "modifica_profilo_utente";
    }

    // Salva le modifiche al profilo utente
    @PostMapping("/profilo/modifica")
    public String salvaModificaProfilo(@ModelAttribute("utente") Utenti utente,
                                       BindingResult bindingResult,
                                       Model model,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes) {
        
        Utenti utenteSessione = (Utenti) session.getAttribute("user");
        if (utenteSessione == null) {
            return "redirect:/utenti/login";
        }

        if (bindingResult.hasErrors()) {
            return "modifica_profilo_utente";
        }

        try {
            // Mantieni l'ID dell'utente corrente
            utente.setId_persona(utenteSessione.getId_persona());
            
            // Mantieni il ruolo esistente (non permettere all'utente di modificarlo)
            utente.setRuolo(utenteSessione.getRuolo());

            // Capitalizza nome e cognome
            utente.setNome(capitalizeEachWord(utente.getNome() != null ? utente.getNome().trim() : ""));
            utente.setCognome(capitalizeEachWord(utente.getCognome() != null ? utente.getCognome().trim() : ""));
            utente.setCodiceFiscale(utente.getCodiceFiscale() != null ? utente.getCodiceFiscale().toUpperCase().trim() : "");
            
            // Gestisci la password: se è vuota, mantieni quella esistente
            if (utente.getPassword() == null || utente.getPassword().trim().isEmpty()) {
                utente.setPassword(utenteSessione.getPassword());
            } else {
                // Valida la nuova password
                String newPassword = utente.getPassword().trim();
                if (newPassword.length() < 8) {
                    model.addAttribute("errorMessage", "La password deve contenere almeno 8 caratteri.");
                    return "modifica_profilo_utente";
                }
                utente.setPassword(newPassword);
            }
            
            // Aggiorna l'utente nel database
            utentiService.updateUtente(utente.getId_persona(), utente);
            
            // Aggiorna i dati in sessione
            Utenti utenteAggiornato = utentiService.getUtenteById(utente.getId_persona());
            if (utenteAggiornato != null) {
                session.setAttribute("user", utenteAggiornato);
                session.setAttribute("userFullName", utenteAggiornato.getNome() + " " + utenteAggiornato.getCognome());
                session.setAttribute("userInitials", getUserInitials(utenteAggiornato));
            }
            
            redirectAttributes.addFlashAttribute("successMessage", "Profilo aggiornato con successo!");
            return "redirect:/area-utente/profilo";
            
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Errore durante l'aggiornamento del profilo. Riprova.");
            return "modifica_profilo_utente";
        }
    }

    // === GESTIONE RICHIESTE DI ADOZIONE ===
    
    // Mostra form per richiesta di adozione
    @GetMapping("/richiesta-adozione")
    public String mostraFormRichiestaAdozione(Model model, HttpSession session) {
        Utenti utente = (Utenti) session.getAttribute("user");
        if (utente == null) {
            return "redirect:/utenti/login";
        }
        
        try {
            model.addAttribute("adozione", new Adozioni());
            model.addAttribute("animali", anagraficaAnimaliService.getByIdStatoAnimale(1));
            model.addAttribute("stepAdozioni", stepAdozioniService.getAllStepAdozioni());
            model.addAttribute("utente", utente);
            return "richiesta_adozione_utente";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Errore nel caricamento della pagina: " + e.getMessage());
            return "area_utente";
        }
    }

    // Salva richiesta di adozione
    @PostMapping("/richiesta-adozione")
    public String salvaRichiestaAdozione(@ModelAttribute("adozione") Adozioni adozione,
                                       BindingResult bindingResult,
                                       Model model,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes,
                                       @RequestParam(value = "note", required = false) String note) {
        
        Utenti utente = (Utenti) session.getAttribute("user");
        if (utente == null) {
            return "redirect:/utenti/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("animali", anagraficaAnimaliService.getAllAnagraficaAnimali());
            model.addAttribute("stepAdozioni", stepAdozioniService.getAllStepAdozioni());
            model.addAttribute("utente", utente);
            return "richiesta_adozione_utente";
        }

        try {
            // Imposta l'utente corrente come richiedente
            adozione.setPersona(utente);
            
            // Salva le note del richiedente
            adozione.setNote(note);
            
            // Imposta lo step iniziale (assumo che ci sia uno step "Richiesta Inviata" o simile)
            // Se non specificato, lascia che il sistema usi il default
            if (adozione.getStepAdozione() == null) {
                // Cerca il primo step disponibile o quello di default
                var stepAdozioni = stepAdozioniService.getAllStepAdozioni();
                if (!stepAdozioni.isEmpty()) {
                    adozione.setStepAdozione(stepAdozioni.get(0));
                }
            }
            
            // Imposta la data corrente se non specificata
            if (adozione.getDataAdozione() == null) {
                adozione.setDataAdozione(new java.sql.Date(System.currentTimeMillis()));
            }
            
            adozioniService.createAdozione(adozione);
            
            redirectAttributes.addFlashAttribute("successMessage", 
                "Richiesta di adozione inviata con successo! Ti contatteremo presto.");
            return "redirect:/area-utente";
            
        } catch (Exception e) {
            model.addAttribute("errorMessage", 
                "Errore durante l'invio della richiesta. Riprova o contatta il rifugio.");
            model.addAttribute("animali", anagraficaAnimaliService.getAllAnagraficaAnimali());
            model.addAttribute("stepAdozioni", stepAdozioniService.getAllStepAdozioni());
            model.addAttribute("utente", utente);
            return "richiesta_adozione_utente";
        }
    }

    // === GESTIONE DONAZIONI UTENTE ===
    
    // Visualizza le donazioni dell'utente
    @GetMapping("/donazioni")
    public String visualizzaDonazioniUtente(Model model, HttpSession session) {
        Utenti utente = (Utenti) session.getAttribute("user");
        if (utente == null) {
            return "redirect:/utenti/login";
        }
        
        try {
            List<Donazioni> donazioni = donazioniService.getDonazioniByUtente(utente.getId_persona());
            
            // Calcola le statistiche delle donazioni
            double importoTotale = donazioni.stream().mapToDouble(Donazioni::getImporto).sum();
            double mediaPerDonazione = donazioni.isEmpty() ? 0.0 : importoTotale / donazioni.size();
            
            model.addAttribute("donazioni", donazioni);
            model.addAttribute("utente", utente);
            model.addAttribute("importoTotale", importoTotale);
            model.addAttribute("mediaPerDonazione", mediaPerDonazione);
            
            return "donazioni_utenti";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Errore nel caricamento delle donazioni: " + e.getMessage());
            return "area_utente";
        }
    }

    // Utility per capitalizzare ogni parola
    private String capitalizeEachWord(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return java.util.Arrays.stream(str.trim().toLowerCase().split("\\s+"))
            .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
            .reduce((w1, w2) -> w1 + " " + w2)
            .orElse("");
    }

    // Utility per ottenere le iniziali dell'utente
    private String getUserInitials(Utenti utente) {
        String nome = utente.getNome() != null ? utente.getNome().trim() : "";
        String cognome = utente.getCognome() != null ? utente.getCognome().trim() : "";
        String initialNome = nome.isEmpty() ? "" : nome.substring(0, 1).toUpperCase();
        String initialCognome = cognome.isEmpty() ? "" : cognome.substring(0, 1).toUpperCase();
        return initialNome + initialCognome;
    }
}
