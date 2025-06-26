package com.rifugio.rifugio.controller;

import com.rifugio.rifugio.entities.Anagrafica_Animali;
import com.rifugio.rifugio.services.AnagraficaAnimaleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class DashboardController {

    @Autowired
    AnagraficaAnimaleServiceImpl anagraficaAnimaleService;

    @GetMapping("/")
    public String dashboardAnimali(Model model) {
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
        return "dashboard_lista_animali";
    }
    
     @GetMapping("/animale/{id}")
    public String dettaglioAnimale(@PathVariable int id, Model model) {
        Anagrafica_Animali animale = anagraficaAnimaleService.getById(id);
        model.addAttribute("animale", animale);
        return "dettaglio_animale"; 
    }


}
