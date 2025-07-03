package com.rifugio.rifugio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.services.AnagraficaAnimaleServiceImpl;
import com.rifugio.rifugio.services.RazzaServiceImpl;
import com.rifugio.rifugio.services.SpecieServiceImpl;
import com.rifugio.rifugio.services.StatoAnimaleServiceImpl;
import com.rifugio.rifugio.services.UtentiService;




@Controller
@RequestMapping("/dashboard")
public class DashboardController {


    @Autowired
    SpecieServiceImpl specieService;

    @Autowired
    RazzaServiceImpl razzaService;

    @Autowired
    StatoAnimaleServiceImpl statoAnimaleService;

    @Autowired
    UtentiService utentiService;

    @Autowired
    private AnagraficaAnimaleServiceImpl anagraficaAnimaleService;



    @GetMapping("/animali")
    public String getAnimali(Model model) {
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
        model.addAttribute("razzaList", razzaService.getAllRazze());    
        return "dashboard_lista_animali";
    }
    
    @GetMapping("/animali/crea")
    public String mostraFormCreazioneAnimale(Model model) {
        model.addAttribute("animale", new AnagraficaAnimali()); 
        model.addAttribute("specieList", specieService.getAllSpecie());  
        model.addAttribute("razzaList", razzaService.getAllRazze());    
        model.addAttribute("statiAnimali", statoAnimaleService.getAllStatiAnimali());  
        return "creazione_animale";  // nome del template Thymeleaf
    }

    @GetMapping("/animali/aggiorna/{id}")
    public String aggiornaAnimale(@PathVariable Integer id, Model model) {
        model.addAttribute("animale", anagraficaAnimaleService.getByIdAnagraficaAnimali(id));
        model.addAttribute("specieList", specieService.getAllSpecie());
        model.addAttribute("razzaList", razzaService.getAllRazze());
        model.addAttribute("statiAnimali", statoAnimaleService.getAllStatiAnimali());
        return "modifica_animale";
    }

}