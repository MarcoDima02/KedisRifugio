package com.rifugio.rifugio.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rifugio.rifugio.entities.Adozioni;
import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.entities.VisiteVeterinarie;
import com.rifugio.rifugio.services.AdozioniServiceImpl;
import com.rifugio.rifugio.services.AnagraficaAnimaliService;
import com.rifugio.rifugio.services.AnagraficaAnimaliServiceImpl;
import com.rifugio.rifugio.services.DonazioniService;
import com.rifugio.rifugio.services.RazzaServiceImpl;
import com.rifugio.rifugio.services.SpecieServiceImpl;
import com.rifugio.rifugio.services.StatoAnimaleServiceImpl;
import com.rifugio.rifugio.services.StepAdozioniService;
import com.rifugio.rifugio.services.UtentiService;
import com.rifugio.rifugio.services.VisiteVeterinarieServiceImpl;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/dashboard/admin")
public class DashboardAdminController {

    @Autowired
    StepAdozioniService stepAdozioniService;

    @Autowired
    AnagraficaAnimaliService anagraficaAnimaliService;

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

    @GetMapping("/animali/{id}")
    public String dettaglioAnimaleAdmin(@PathVariable Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("animale", anagraficaAnimaleService.getByIdAnagraficaAnimali(id));
        model.addAttribute("adminView", true);
        return "dettaglio_animale";
    }
    
    @GetMapping("/animali/save")
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

    @PostMapping("/animali/save")
    public String salvaAnimale(@Validated @ModelAttribute("animale") AnagraficaAnimali animale,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            // se ci sono errori di validazione, ritorna al form
            model.addAttribute("specieList", specieService.getAllSpecie());
            model.addAttribute("razzaList", razzaService.getAllRazze());
            model.addAttribute("statiAnimali", statoAnimaleService.getAllStatiAnimali());
            return "creazione_animale";
        }

        anagraficaAnimaleService.create(animale); // salva l'animale
        return "redirect:/animali"; // redirect alla lista animali dopo il salvataggio
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

    @GetMapping("/visite-veterinarie/save")
    public String mostraFormCreazioneVisita(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("visita", new VisiteVeterinarie());
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
        return "creazione_visita_veterinaria";
    }

    @GetMapping("/visite-veterinarie/update/{id}")
    public String aggiornaVisita(@PathVariable Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("visita", visiteVeterinarieService.getVisitaById(id));
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
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
        if (visita.getData() == null) {
            bindingResult.rejectValue("data", "error.visita", "La data è obbligatoria.");
        }
        if (visita.getOra() == null) {
            bindingResult.rejectValue("ora", "error.visita", "L'ora è obbligatoria.");
        }
        if (visita.getId_animale() == null) {
            bindingResult.rejectValue("id_animale", "error.visita", "Seleziona un animale.");
        }
        if (visita.getMotivo() == null || visita.getMotivo().isEmpty()) {
            bindingResult.rejectValue("motivo", "error.visita", "Il motivo è obbligatorio.");
        }
        if (visita.getEsito() == null || visita.getEsito().isEmpty()) {
            bindingResult.rejectValue("esito", "error.visita", "Seleziona un esito.");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
            return "modifica_visita_veterinaria";
        }
        visiteVeterinarieService.updateVisita(visita);
        return "redirect:/dashboard/admin/visite-veterinarie";
    }

    @PostMapping("/visite-veterinarie/save")
    public String creaVisita(@Validated @ModelAttribute("visita") VisiteVeterinarie visita,
                         BindingResult bindingResult,
                         Model model,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
            return "creazione_visita_veterinaria";
        }
        if (visita.getEsito() == null || visita.getEsito().isEmpty()) {
            visita.setEsito("In attesa");
        }
        visiteVeterinarieService.addVisita(visita);
        redirectAttributes.addFlashAttribute("successMessage", "Visita veterinaria registrata con successo!");
        return "redirect:/dashboard/admin/visite-veterinarie";
    }

    @GetMapping("/visite-veterinarie/{id}")
    public String dettaglioVisita(@PathVariable Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("visita", visiteVeterinarieService.getVisitaById(id));
        return "dettaglio_visita_veterinaria";
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

    @GetMapping("/adozioni/save")
    public String mostraFormCreazioneAdozione(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("adozione", new Adozioni());
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
        model.addAttribute("utenti", utentiService.getAllUtenti());
        model.addAttribute("stepAdozioni", stepAdozioniService.getAllStepAdozioni());
        return "creazione_adozione";
    }

    @PostMapping("/adozioni/save")
    public String creaAdozione(@Validated @ModelAttribute("adozione") Adozioni adozione,
                         BindingResult bindingResult,
                         Model model,
                         HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
            model.addAttribute("utenti", utentiService.getAllUtenti());
            model.addAttribute("stepAdozioni", stepAdozioniService.getAllStepAdozioni());
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            return "creazione_adozione";
        }
        adozioniService.createAdozione(adozione);
        return "redirect:/dashboard/admin/adozioni";
    }

    @GetMapping("/adozioni/update/{id}")
    public String aggiornaAdozione(@PathVariable Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
        model.addAttribute("utenti", utentiService.getAllUtenti());
        model.addAttribute("stepAdozioni", stepAdozioniService.getAllStepAdozioni());
        model.addAttribute("adozione", adozioniService.getAdozioneById(id));
        return "modifica_adozione";
    }

    @PostMapping("/adozioni/update/{id}")
    public String aggiornaAdozione(@PathVariable Integer id,
                                    @Validated @ModelAttribute("adozione") Adozioni adozione,
                                    BindingResult bindingResult,
                                    Model model,
                                    HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("adozione", adozioniService.getAdozioneById(id));
            return "modifica_adozione";
        }
        adozioniService.updateAdozione(id, adozione);
        return "redirect:/dashboard/admin/adozioni";
    }
    
    // UTENTI

    @GetMapping("/utenti")
    public String getUtenti(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("utenti", utentiService.getAllUtenti());
        return "dashboard_lista_utenti";
    }

    @GetMapping("/utenti/update/{id}")
    public String mostraFormModificaUtente(@PathVariable Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("utente", utentiService.getUtenteById(id));
        return "modifica_utente";
    }

    @PostMapping("/utenti/update/{id}")
    public String aggiornaUtente(@PathVariable Integer id,
                                 @ModelAttribute("utente") com.rifugio.rifugio.entities.Utenti utente,
                                 BindingResult result,
                                 Model model,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        // Validazione custom lato server (aggiungi qui se serve)
        if (result.hasErrors()) {
            model.addAttribute("utente", utente);
            return "modifica_utente";
        }
        // Capitalizzazione e upper case come in registrazione
        utente.setNome(capitalizeEachWord(utente.getNome() != null ? utente.getNome().trim() : ""));
        utente.setCognome(capitalizeEachWord(utente.getCognome() != null ? utente.getCognome().trim() : ""));
        utente.setCodiceFiscale(utente.getCodiceFiscale() != null ? utente.getCodiceFiscale().toUpperCase().trim() : "");
        utentiService.updateUtente(id, utente);
        redirectAttributes.addFlashAttribute("successMessage", "Utente aggiornato con successo.");
        return "redirect:/dashboard/admin/utenti";
    }

    // Utility per capitalizzare ogni parola (già presente in UtentiController, la copio qui)
    private String capitalizeEachWord(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return java.util.Arrays.stream(str.trim().toLowerCase().split("\\s+"))
            .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
            .reduce((w1, w2) -> w1 + " " + w2)
            .orElse("");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
            }
            @Override
            public String getAsText() {
                LocalDate value = (LocalDate) getValue();
                return (value != null) ? value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
            }
        });
    }

}