package com.rifugio.rifugio.controller;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.services.AnagraficaAnimaliServiceImpl;
import com.rifugio.rifugio.services.RazzaServiceImpl;
import com.rifugio.rifugio.services.SpecieServiceImpl;
import com.rifugio.rifugio.services.StatoAnimaleServiceImpl;

import java.util.List;

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
import jakarta.servlet.http.HttpSession;



@Controller
public class RifugioController {

    @Autowired
    AnagraficaAnimaliServiceImpl anagraficaAnimaleService;

    @Autowired
    SpecieServiceImpl specieService;

    @Autowired
    RazzaServiceImpl razzaService;

    @Autowired
    StatoAnimaleServiceImpl statoAnimaleService;

    @GetMapping("/")
    public String homePagine(Model model, HttpSession session){
        model.addAttribute("animaliDisponibili", anagraficaAnimaleService.getByIdStatoAnimale(1).size());
        // Le variabili userFullName e userInitials sono già aggiunte dal ModelAttribute di UtentiController se l'utente è loggato
        return "home";
    }

    @GetMapping("/animali")
    public String dashboardAnimali(Model model) {
        String[] sessoList = {"Maschio", "Femmina"};
        model.addAttribute("animali", anagraficaAnimaleService.getByIdStatoAnimale(1)); // Serve per mostrare solo gli animali disponibili
        model.addAttribute("specieList", specieService.getAllSpecie());
        model.addAttribute("razzaList", razzaService.getAllRazze());
        model.addAttribute("selezionata", ""); // valore selezionato di default
        model.addAttribute("sessoList", sessoList); // valore selezionato di default
        return "lista_animali";
    }
    
    @GetMapping("/animale/{id}")
    public String dettaglioAnimale(@PathVariable int id, Model model) {
        AnagraficaAnimali animale = anagraficaAnimaleService.getByIdAnagraficaAnimali(id);
        model.addAttribute("animale", animale);
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

        // Ri-popoliamo le liste di selezione
        String[] sessoList = {"M", "F"}; // Maschio = M, Femmina = F

        model.addAttribute("animali", animaliFiltrati);
        model.addAttribute("specieList", specieService.getAllSpecie());
        model.addAttribute("razzaList", razzaService.getAllRazze());
        model.addAttribute("sessoList", sessoList);

        return "lista_animali";
    }


}
