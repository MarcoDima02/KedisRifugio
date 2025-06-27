package com.rifugio.rifugio.controller;

import com.rifugio.rifugio.entities.Anagrafica_Animali;
import com.rifugio.rifugio.services.AnagraficaAnimaleServiceImpl;
import com.rifugio.rifugio.services.RazzaServiceImpl;
import com.rifugio.rifugio.services.SpecieServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class DashboardController {

    @Autowired
    AnagraficaAnimaleServiceImpl anagraficaAnimaleService;

    @Autowired
    SpecieServiceImpl specieService;

    @Autowired
    RazzaServiceImpl razzaService;

    @GetMapping("/")
    public String dashboardAnimali(Model model) {
        String[] sessoList = {"Maschio", "Femmina"};
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
        model.addAttribute("specieList", specieService.getAllSpecie());
        model.addAttribute("razzaList", razzaService.getAllRazze());
        model.addAttribute("selezionata", ""); // valore selezionato di default
        model.addAttribute("sessoList", sessoList); // valore selezionato di default
        return "dashboard_lista_animali";
    }
    
    @GetMapping("/animale/{id}")
    public String dettaglioAnimale(@PathVariable int id, Model model) {
        Anagrafica_Animali animale = anagraficaAnimaleService.getById(id);
        model.addAttribute("animale", animale);
        return "dettaglio_animale"; 
    }


}
