package com.rifugio.rifugio.controller;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.services.AnagraficaAnimaleServiceImpl;
import com.rifugio.rifugio.services.RazzaServiceImpl;
import com.rifugio.rifugio.services.SpecieServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class RifugioController {

    @Autowired
    AnagraficaAnimaleServiceImpl anagraficaAnimaleService;

    @Autowired
    SpecieServiceImpl specieService;

    @Autowired
    RazzaServiceImpl razzaService;

    @GetMapping("/")
    public String homePagine(Model model){
        model.addAttribute("animaliDisponibili", anagraficaAnimaleService.getByIdStatoAnimale(1).size());
        return "home";
    }

    @GetMapping("/animali")
    public String dashboardAnimali(Model model) {
        String[] sessoList = {"Maschio", "Femmina"};
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
        model.addAttribute("specieList", specieService.getAllSpecie());
        model.addAttribute("razzaList", razzaService.getAllRazze());
        model.addAttribute("selezionata", ""); // valore selezionato di default
        model.addAttribute("sessoList", sessoList); // valore selezionato di default
        return "dashboard_lista_animali";
    }

    @GetMapping("/speciee/{id}")
    public String dashboardAnimaliSpecie(Model model, @PathVariable int id) {
        String[] sessoList = {"Maschio", "Femmina"};
        model.addAttribute("animali", anagraficaAnimaleService.getByIdSpecie(id));
        model.addAttribute("specieList", specieService.getAllSpecie());
        model.addAttribute("razzaList", razzaService.getAllRazze());
        model.addAttribute("selezionata", ""); // valore selezionato di default
        model.addAttribute("sessoList", sessoList); // valore selezionato di default
        return "dashboard_lista_animali";
    }
    
    
    @GetMapping("/animale/{id}")
    public String dettaglioAnimale(@PathVariable int id, Model model) {
        AnagraficaAnimali animale = anagraficaAnimaleService.getByIdAnagraficaAnimali(id);
        model.addAttribute("animale", animale);
        return "dettaglio_animale"; 
    }

    @GetMapping("/filtrati")
    public String filtraAnimali(
        @RequestParam(required = false) int specie,
        Model model
    ) {
        List<AnagraficaAnimali> animaliFiltrati = anagraficaAnimaleService.getByIdSpecie(specie);
        model.addAttribute("animali", animaliFiltrati);

        // eventualmente ri-popolare le liste per i filtri
        String[] sessoList = {"Maschio", "Femmina"};
        model.addAttribute("animali", anagraficaAnimaleService.getByIdSpecie(specie));
        model.addAttribute("specieList", specieService.getAllSpecie());
        model.addAttribute("razzaList", razzaService.getAllRazze());
        model.addAttribute("selezionata", ""); // valore selezionato di default
        model.addAttribute("sessoList", sessoList); // valore selezionato di default



        return "dashboard_lista_animali"; // il nome del template Thymeleaf da mostrare
    }


}
