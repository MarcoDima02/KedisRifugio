package com.rifugio.rifugio.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.entities.VisiteVeterinarie;
import com.rifugio.rifugio.services.AdozioniServiceImpl;
import com.rifugio.rifugio.services.AnagraficaAnimaliServiceImpl;
import com.rifugio.rifugio.services.DonazioniService;
import com.rifugio.rifugio.services.RazzaServiceImpl;
import com.rifugio.rifugio.services.SpecieServiceImpl;
import com.rifugio.rifugio.services.StatoAnimaleServiceImpl;
import com.rifugio.rifugio.services.UtentiService;
import com.rifugio.rifugio.services.VisiteVeterinarieServiceImpl;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/dashboard/admin")
public class DashboardAdminController {

    @Autowired
    SpecieServiceImpl specieService;

    @Autowired
    RazzaServiceImpl razzaService;

    @Autowired
    StatoAnimaleServiceImpl statoAnimaleService;

    @Autowired
    UtentiService utentiService;

    @Autowired
    private AnagraficaAnimaliServiceImpl anagraficaAnimaleService;

    @Autowired
    private DonazioniService donazioniService;

    @Autowired
    private VisiteVeterinarieServiceImpl visiteVeterinarieService;

    @Autowired
    private AdozioniServiceImpl adozioniService;

    // Controllo ruolo admin
    private boolean isAdmin(HttpSession session) {
        Object ruolo = session.getAttribute("userRuolo");
        return ruolo != null && ruolo.toString().equalsIgnoreCase("ADMIN");
    }

    // DASHBOARD HOME

    @GetMapping
    public String dashboardAdmin(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        // Statistiche rapide
        int numUtenti = utentiService.getAllUtenti().size();
        int numAnimali = anagraficaAnimaleService.getAllAnagraficaAnimali().size();
        int numAdozioni = adozioniService.getAllAdozioni().size();
        double totDonazioni = donazioniService.getAllDonazioni().stream().mapToDouble(d -> d.getImporto()).sum();
        model.addAttribute("numUtenti", numUtenti);
        model.addAttribute("numAnimali", numAnimali);
        model.addAttribute("numAdozioni", numAdozioni);
        model.addAttribute("totDonazioni", String.format("%.2f", totDonazioni));
        return "dashboard_admin";
    }

    // DASHBOARD ANIMALI

    @GetMapping("/animali")
    public String getAnimali(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
        model.addAttribute("razzaList", razzaService.getAllRazze());    
        return "dashboard_lista_animali";
    }
    
    @GetMapping("/animali/crea")
    public String mostraFormCreazioneAnimale(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("animale", new AnagraficaAnimali()); 
        model.addAttribute("specieList", specieService.getAllSpecie());  
        model.addAttribute("razzaList", razzaService.getAllRazze());    
        model.addAttribute("statiAnimali", statoAnimaleService.getAllStatiAnimali());  
        return "creazione_animale";  // nome del template Thymeleaf
    }

    @GetMapping("/animali/update/{id}")
    public String aggiornaAnimale(@PathVariable Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("animale", anagraficaAnimaleService.getByIdAnagraficaAnimali(id));
        model.addAttribute("specieList", specieService.getAllSpecie());
        model.addAttribute("razzaList", razzaService.getAllRazze());
        model.addAttribute("statiAnimali", statoAnimaleService.getAllStatiAnimali());
        return "modifica_animale";
    }

    @PostMapping("/animali/update/{id}")
    public String aggiornaAnimale(@PathVariable Integer id,
                                    @Validated @ModelAttribute("animale") AnagraficaAnimali animale,
                                    BindingResult bindingResult,
                                    Model model,
                                    HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("specieList", specieService.getAllSpecie());
            model.addAttribute("razzaList", razzaService.getAllRazze());
            model.addAttribute("statiAnimali", statoAnimaleService.getAllStatiAnimali());
            return "modifica_animale";
        }
        anagraficaAnimaleService.update(id, animale); // aggiorna l'animale
        return "redirect:/dashboard/admin/animali";
    }

    // DASHBOARD DONAZIONI

    @GetMapping("/donazioni")
    public String getAllDonazioni(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        List<Donazioni> donazioni = donazioniService.getAllDonazioni();
        model.addAttribute("donazioni", donazioni);
        return "dashboard_lista_donazioni";
    }

    @GetMapping("/donazioni/update/{id}")
    public String aggiornaDonazione(@PathVariable Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("donazione", donazioniService.getByIdDonazione(id));
        return "modifica_donazione";
    }

    @PostMapping("/donazioni/update/{id}")
    public String aggiornaDonazione(@PathVariable Integer id,
                                      @Validated @ModelAttribute("donazione") Donazioni donazione,
                                      BindingResult bindingResult,
                                      Model model,
                                      HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("donazione", donazioniService.getByIdDonazione(id));
            return "modifica_donazione";
        }
        donazioniService.update(id, donazione); // aggiorna la donazione
        return "redirect:/dashboard/admin/donazioni";
    }

    // DASHBOARD VISITE VETERINARIE

    @GetMapping("/visite-veterinarie")
    public String getVisiteVeterinarie(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("visiteVeterinarie", visiteVeterinarieService.getAllVisiteVeterinarie());
        return "dashboard_lista_visite_veterinarie";
    }

    @GetMapping("/visite-veterinarie/crea")
    public String mostraFormCreazioneVisita(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("visita", new VisiteVeterinarie());
        return "creazione_visita_veterinaria";  // nome del template Thymeleaf
    }

    @GetMapping("/visite-veterinarie/update/{id}")
    public String aggiornaVisita(@PathVariable Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("visita", visiteVeterinarieService.getVisitaById(id));
        return "modifica_visita_veterinaria";
    }

    @PostMapping("/visite-veterinarie/update/{id}")
    public String aggiornaVisita(@PathVariable Integer id,
                                    @Validated @ModelAttribute("visita") VisiteVeterinarie visita,
                                    BindingResult bindingResult,
                                    Model model,
                                    HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("visita", visiteVeterinarieService.getVisitaById(id));
            return "modifica_visita_veterinaria";
        }
        visiteVeterinarieService.updateVisita(visita);
        return "redirect:/dashboard/admin/visite-veterinarie";
    }

    // DASHBOARD ADOZIONI

    @GetMapping("/adozioni")
    public String getAdozioni(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("adozioni", adozioniService.getAllAdozioni());
        return "dashboard_lista_adozioni";
    }
    

}