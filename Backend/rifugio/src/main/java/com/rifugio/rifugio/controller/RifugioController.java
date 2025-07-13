package com.rifugio.rifugio.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.entities.Immagine;
import com.rifugio.rifugio.entities.Utenti;
import com.rifugio.rifugio.services.AnagraficaAnimaliServiceImpl;
import com.rifugio.rifugio.services.DonazioniServiceImpl;
import com.rifugio.rifugio.services.ImmagineServiceImpl;
import com.rifugio.rifugio.services.RazzaServiceImpl;
import com.rifugio.rifugio.services.SpecieServiceImpl;
import com.rifugio.rifugio.services.StatoAnimaleServiceImpl;
import com.rifugio.rifugio.services.UtentiServiceImpl;

import jakarta.servlet.http.HttpSession;


@Controller
public class RifugioController {


    @Autowired
    AnagraficaAnimaliServiceImpl anagraficaAnimaleService;
    
    @Autowired
    DonazioniServiceImpl donazioniServiceImpl;

    @Autowired
    UtentiServiceImpl utentiServiceImpl;

    @Autowired
    SpecieServiceImpl specieService;

    @Autowired
    RazzaServiceImpl razzaService;

    @Autowired
    StatoAnimaleServiceImpl statoAnimaleService;

    @Autowired
    ImmagineServiceImpl immagineService;

    @GetMapping("/")
    public String homePagine(Model model, HttpSession session){
        model.addAttribute("animaliDisponibili", anagraficaAnimaleService.getByIdStatoAnimale(1).size());
        // Le variabili userFullName e userInitials sono già aggiunte dal ModelAttribute di UtentiController se l'utente è loggato
        return "home";
    }

    @GetMapping("/animali")
    public String dashboardAnimali(Model model) {
        String[] sessoList = {"Maschio", "Femmina"};
        List<AnagraficaAnimali> animali = anagraficaAnimaleService.getByIdStatoAnimale(1);
        
        // Per ogni animale, ottieni l'immagine principale
        Map<Integer, Optional<Immagine>> immaginiPrincipali = new HashMap<>();
        for (AnagraficaAnimali animale : animali) {
            Optional<Immagine> immaginePrincipale = immagineService.getImmaginePrincipaleByAnimale(animale.getIdAnimale());
            immaginiPrincipali.put(animale.getIdAnimale(), immaginePrincipale);
        }
        
        model.addAttribute("animali", animali);
        model.addAttribute("immaginiPrincipali", immaginiPrincipali);
        model.addAttribute("specieList", specieService.getAllSpecie());
        model.addAttribute("razzaList", razzaService.getAllRazze());
        model.addAttribute("selezionata", ""); // valore selezionato di default
        model.addAttribute("sessoList", sessoList); // valore selezionato di default
        return "lista_animali";
    }
    
    @GetMapping("/animale/{id}")
    public String dettaglioAnimale(@PathVariable int id, Model model) {
        AnagraficaAnimali animale = anagraficaAnimaleService.getByIdAnagraficaAnimali(id);
        
        // Ottieni l'immagine principale
        Optional<Immagine> immaginePrincipale = immagineService.getImmaginePrincipaleByAnimale(id);
        
        // Ottieni tutte le immagini ordinate per il carosello
        List<Immagine> immaginiCarosello = immagineService.getImmaginiByAnimaleOrdered(id);
        
        model.addAttribute("animale", animale);
        model.addAttribute("immaginePrincipale", immaginePrincipale);
        model.addAttribute("immaginiCarosello", immaginiCarosello);
        model.addAttribute("adminView", false); // Vista pubblica
        return "dettaglio_animale"; 
    }

    @GetMapping("/filtrati")
    public String filtraAnimali(
        @RequestParam(required = false) Integer specie,
        @RequestParam(required = false) Integer razza,
        @RequestParam(required = false) Character sesso,
        Model model
    ) {
        // Otteniamo la lista filtrata
        List<AnagraficaAnimali> animaliFiltrati = anagraficaAnimaleService.filtra(specie, razza, sesso);

        // Per ogni animale, ottieni l'immagine principale
        Map<Integer, Optional<Immagine>> immaginiPrincipali = new HashMap<>();
        for (AnagraficaAnimali animale : animaliFiltrati) {
            Optional<Immagine> immaginePrincipale = immagineService.getImmaginePrincipaleByAnimale(animale.getIdAnimale());
            immaginiPrincipali.put(animale.getIdAnimale(), immaginePrincipale);
        }

        // Ri-popoliamo le liste di selezione
        String[] sessoList = {"M", "F"}; // Maschio = M, Femmina = F

        model.addAttribute("animali", animaliFiltrati);
        model.addAttribute("immaginiPrincipali", immaginiPrincipali);
        model.addAttribute("specieList", specieService.getAllSpecie());
        model.addAttribute("razzaList", razzaService.getAllRazze());
        model.addAttribute("sessoList", sessoList);

        return "lista_animali";
    }

    @GetMapping("/come-aiutare")
    public String sezioneComeAiutare() {
        return "come_aiutare";
    }

    @GetMapping("/donazione")
    public String paginaDonazione(HttpSession session, Model model) {
        if (session.getAttribute("userRuolo") == null) {
            return "redirect:/utenti/login";
        }
        model.addAttribute("donazione", new Donazioni());
        return "donazione";
    }

    @PostMapping("/donazione/save")
    public String salvaDonazione(@Validated @ModelAttribute("donazione") Donazioni donazione,
                           BindingResult bindingResult, HttpSession session,
                           Model model) 
    {
        if (session.getAttribute("userRuolo") == null) {
            return "redirect:/utenti/login";
        }
        if (bindingResult.hasErrors()) {
            return "donazione";
        }
        
        // Recupera l'oggetto Utenti direttamente dalla sessione
        Utenti utente = (Utenti) session.getAttribute("user");
        if (utente == null) {
            return "redirect:/utenti/login";
        }
        
        // Associa l'utente alla donazione e salva
        donazione.setPersona(utente);

        Date currentDate = new Date(System.currentTimeMillis());
        donazione.setData_donazione(currentDate);
 
        donazioniServiceImpl.save(donazione);
        
        return "donazione_successo";
    }
    


}
