package com.rifugio.rifugio.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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

import com.rifugio.rifugio.dto.AttivitaRecente;
import com.rifugio.rifugio.entities.Adozioni;
import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.entities.CartellaClinica;
import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.entities.Immagine;
import com.rifugio.rifugio.entities.Razza;
import com.rifugio.rifugio.entities.Specie;
import com.rifugio.rifugio.entities.Utenti;
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
    private com.rifugio.rifugio.services.ImmagineService immagineService;

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
        int numUtenti = utentiService.getAllUtentiAttivi().size(); // Solo utenti attivi per le statistiche
        int numAnimali = anagraficaAnimaleService.getAllAnagraficaAnimali().size();
        int numAdozioni = adozioniService.getAllAdozioni().size();
        double totDonazioni = donazioniService.getAllDonazioni().stream().mapToDouble(d -> d.getImporto()).sum();
        model.addAttribute("numUtenti", numUtenti);
        model.addAttribute("numAnimali", numAnimali);
        model.addAttribute("numAdozioni", numAdozioni);
        model.addAttribute("totDonazioni", String.format("%.2f", totDonazioni));
        
        // Attività recenti
        List<AttivitaRecente> attivitaRecenti = new ArrayList<>();
        
        // Aggiungi ultime donazioni
        List<Donazioni> ultimeDonazioni = donazioniService.getUltimeDonazioni();
        for (Donazioni donazione : ultimeDonazioni) {
            LocalDateTime dataOra = donazione.getData_donazione(); // Ora include già data e ora
            String dettagli = String.format("€%.2f da %s %s", 
                donazione.getImporto(),
                donazione.getPersona().getNome(),
                donazione.getPersona().getCognome());
            attivitaRecenti.add(new AttivitaRecente(dataOra, "Donazione", dettagli, "Ricevuta", "badge-success"));
        }
        
        // Aggiungi ultime adozioni
        List<Adozioni> ultimeAdozioni = adozioniService.getUltimeAdozioni();
        for (Adozioni adozione : ultimeAdozioni) {
            LocalDateTime dataOra = adozione.getDataAdozione().toLocalDate().atStartOfDay();
            String dettagli = String.format("%s adottato da %s %s", 
                adozione.getAnimale().getNome(),
                adozione.getPersona().getNome(),
                adozione.getPersona().getCognome());
            String stato = adozione.getStepAdozione() != null ? adozione.getStepAdozione().getNome_step() : "In corso";
            String badgeClass = stato.contains("Completata") ? "badge-success" : "badge-info";
            attivitaRecenti.add(new AttivitaRecente(dataOra, "Adozione", dettagli, stato, badgeClass));
        }
        
        // Aggiungi ultime visite veterinarie (solo quelle passate)
        List<VisiteVeterinarie> ultimeVisite = visiteVeterinarieService.getUltimeVisite();
        for (VisiteVeterinarie visita : ultimeVisite) {
            LocalDateTime dataOra = visita.getData().atTime(visita.getOra());
            String dettagli = String.format("%s - %s", 
                visita.getId_animale().getNome(),
                visita.getMotivo());
            // Le visite passate sono sempre considerate completate
            String stato = visita.getEsito() != null && !visita.getEsito().isEmpty() ? "Completata" : "Effettuata";
            String badgeClass = "badge-success";
            attivitaRecenti.add(new AttivitaRecente(dataOra, "Visita Veterinaria", dettagli, stato, badgeClass));
        }
        
        // Aggiungi prossime visite veterinarie (solo quelle future o di oggi)
        List<VisiteVeterinarie> prossimeVisite = visiteVeterinarieService.getProssimeVisite();
        for (VisiteVeterinarie visita : prossimeVisite) {
            LocalDateTime dataOra = visita.getData().atTime(visita.getOra());
            String dettagli = String.format("%s - %s", 
                visita.getId_animale().getNome(),
                visita.getMotivo());
            // Determina lo stato basato su data/ora e risultato
            String stato;
            String badgeClass;
            
            if (dataOra.isBefore(LocalDateTime.now())) {
                // Visita già passata ma recuperata come "prossima" - non dovrebbe succedere
                stato = visita.getEsito() != null && !visita.getEsito().isEmpty() ? "Completata" : "Effettuata";
                badgeClass = "badge-success";
            } else {
                // Visita futura
                stato = "Programmata";
                badgeClass = "badge-warning";
            }
            
            attivitaRecenti.add(new AttivitaRecente(dataOra, "Visita Veterinaria", dettagli, stato, badgeClass));
        }
        
        // Ordina per data decrescente e prendi solo i primi 10
        attivitaRecenti.sort(Comparator.comparing(AttivitaRecente::getDataOra).reversed());
        if (attivitaRecenti.size() > 10) {
            attivitaRecenti = attivitaRecenti.subList(0, 10);
        }
        
        model.addAttribute("attivitaRecenti", attivitaRecenti);
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
        var animale = anagraficaAnimaleService.getByIdAnagraficaAnimali(id);
        
        // Ottieni l'immagine principale
        var immaginePrincipale = immagineService.getImmaginePrincipaleByAnimale(id);
        
        // Ottieni tutte le immagini ordinate per il carosello
        var immaginiCarosello = immagineService.getImmaginiByAnimaleOrdered(id);
        
        model.addAttribute("animale", animale);
        model.addAttribute("immaginePrincipale", immaginePrincipale);
        model.addAttribute("immaginiCarosello", immaginiCarosello);
        model.addAttribute("adminView", true);
        return "dettaglio_animale";
    }
    
    @GetMapping("/animali/save")
    public String mostraFormCreazioneAnimale(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        
        // Crea un nuovo animale con cartella clinica inizializzata
        AnagraficaAnimali animale = new AnagraficaAnimali();
        CartellaClinica cartellaClinica = new CartellaClinica();
        animale.setIdCartellaClinica(cartellaClinica);
        
        model.addAttribute("animale", animale); 
        model.addAttribute("specieList", specieService.getAllSpecie());  
        model.addAttribute("razzaList", razzaService.getAllRazze());    
        model.addAttribute("statiAnimali", statoAnimaleService.getAllStatiAnimali());  
        return "creazione_animale";  // nome del template Thymeleaf
    }

    @PostMapping("/animali/save")
    public String salvaAnimale(
        @Validated @ModelAttribute("animale") AnagraficaAnimali animale,
        BindingResult bindingResult,
        Model model,
        @org.springframework.web.bind.annotation.RequestParam(value = "immagini", required = false) List<org.springframework.web.multipart.MultipartFile> immagini,
        @org.springframework.web.bind.annotation.RequestParam(value = "mainImageIndex", required = false, defaultValue = "0") Integer mainImageIndex
    ) {
        // Debug logging
        System.out.println("=== DEBUG CREAZIONE ANIMALE ===");
        System.out.println("Nome: " + animale.getNome());
        System.out.println("Specie: " + (animale.getSpecie() != null ? animale.getSpecie().getNome() : "null"));
        System.out.println("Razza: " + (animale.getRazza() != null ? animale.getRazza().getNome() : "null"));
        System.out.println("Sesso: " + animale.getSesso());
        System.out.println("Data nascita: " + animale.getDataNascita());
        System.out.println("Data arrivo: " + animale.getDataArrivo());
        System.out.println("Stato animale: " + (animale.getIdStatoAnimale() != null ? animale.getIdStatoAnimale().getDescrizione() : "null"));
        System.out.println("Immagini caricate: " + (immagini != null ? immagini.size() : 0));
        System.out.println("Main image index: " + mainImageIndex);
        System.out.println("Errori binding: " + bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            System.out.println("Errori: " + bindingResult.getAllErrors());
        }
        System.out.println("===============================");
        
        if (bindingResult.hasErrors()) {
            // se ci sono errori di validazione, ritorna al form
            model.addAttribute("specieList", specieService.getAllSpecie());
            model.addAttribute("razzaList", razzaService.getAllRazze());
            model.addAttribute("statiAnimali", statoAnimaleService.getAllStatiAnimali());
            return "creazione_animale";
        }

        // Salva l'animale
        AnagraficaAnimali animaleSalvato = anagraficaAnimaleService.create(animale);
        System.out.println("Animale salvato con ID: " + animaleSalvato.getIdAnagraficaAnimali());

        // Salva le immagini se presenti
        if (immagini != null && !immagini.isEmpty()) {
            System.out.println("Salvando " + immagini.size() + " immagini...");
            // Salva tutte le immagini
            List<Immagine> immaginiSalvate = immagineService.storeImmagini(immagini, animaleSalvato.getIdAnagraficaAnimali());
            
            // Imposta l'immagine principale se specificata
            if (mainImageIndex != null && mainImageIndex >= 0 && mainImageIndex < immaginiSalvate.size()) {
                Immagine immaginePrincipale = immaginiSalvate.get(mainImageIndex);
                try {
                    immagineService.setImmaginePrincipale(immaginePrincipale.getId_immagine(), animaleSalvato.getIdAnagraficaAnimali());
                    System.out.println("Immagine principale impostata: " + immaginePrincipale.getId_immagine());
                } catch (Exception e) {
                    // Log error ma non bloccare il processo
                    System.err.println("Errore impostazione immagine principale: " + e.getMessage());
                }
            }
        }

        return "redirect:/dashboard/admin/animali"; // redirect alla lista animali dopo il salvataggio
    }

    @GetMapping("/animali/update/{id}")
    public String aggiornaAnimale(@PathVariable Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        var animale = anagraficaAnimaliService.getByIdAnagraficaAnimali(id);
        model.addAttribute("animale", animale);
        // Passa anche le immagini associate ordinate
        var immagini = immagineService.getImmaginiByAnimaleOrdered(id);
        model.addAttribute("immagini", immagini);
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

    // DASHBOARD RAZZE

    @GetMapping("/razze")
    public String getAllRazze(Model model, HttpSession session){
        if (!isAdmin(session)) {
            return "redirect:/";
        }

        model.addAttribute("razze", razzaService.getAllRazze());
        return "dashboard_lista_razze";  

    }

    @GetMapping("/razze/save")
    public String formSaveRazza(Model model, HttpSession session){
        if (!isAdmin(session)) {
            return "redirect:/";
        }

        model.addAttribute("razza", new Razza());
        model.addAttribute("specie", specieService.getAllSpecie());
        return "creazione_razza";  

    }

    @PostMapping("/razze/update/{id}")
    public String updateRazze(@PathVariable Integer id,
            Model model,
            HttpSession session,
            @Validated @ModelAttribute("razza") Razza razza,
            BindingResult bindingResult) {

        if (!isAdmin(session)) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("specie", specieService.getAllSpecie());
            return "modifica_razza";  
        }

        // Aggiorna razza
        razzaService.aggiorna(id, razza);

        return "redirect:/dashboard/admin/razze";
    }

    @GetMapping("/razze/update/{id}")
    public String formUpdateRazza(@PathVariable Integer id, Model model, HttpSession session){
        if (!isAdmin(session)) {
            return "redirect:/";
        }

        model.addAttribute("razza", razzaService.getRazzaById(id));
        model.addAttribute("specie", specieService.getAllSpecie());
        return "modifica_razza";  

    }

    @PostMapping("/razze/save")
    public String saveRazza(
            Model model,
            HttpSession session,
            @Validated @ModelAttribute("razza") Razza razza,
            BindingResult bindingResult) {

        if (!isAdmin(session)) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("specie", specieService.getAllSpecie());
            return "creazione_razza";  
        }

        // Salva razza
        razzaService.salva(razza);

        return "redirect:/dashboard/admin/razze";
    }



    // DASHBOARD DONAZIONI

    @GetMapping("/donazioni")
    public String getAllDonazioni(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("donazioni", donazioniService.getAllDonazioni());
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

    @GetMapping("/donazioni/save")
    public String mostraFormCreazioneDonazione(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("donazione", new Donazioni());
        model.addAttribute("utenti", utentiService.getAllUtentiAttivi()); // Solo utenti attivi per le donazioni
        return "creazione_donazione_admin";
    }

    @PostMapping("/donazioni/save")
    public String creaDonazione(@org.springframework.web.bind.annotation.RequestParam("personaId") Integer personaId,
                         @org.springframework.web.bind.annotation.RequestParam("importo") Double importo,
                         @org.springframework.web.bind.annotation.RequestParam(value = "data_donazione", required = false) String dataDonazioneStr,
                         @org.springframework.web.bind.annotation.RequestParam("metodo_pagamento") String metodoPagamento,
                         @org.springframework.web.bind.annotation.RequestParam(value = "note", required = false) String note,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        
        // Debug logging
        System.out.println("=== DEBUG DONAZIONE ===");
        System.out.println("PersonaId ricevuto: " + personaId);
        System.out.println("Importo ricevuto: " + importo);
        System.out.println("Data donazione string: " + dataDonazioneStr);
        System.out.println("Metodo pagamento: " + metodoPagamento);
        System.out.println("Note: " + note);
        System.out.println("========================");
        
        // Validazioni manuali
        if (personaId == null || personaId <= 0) {
            redirectAttributes.addFlashAttribute("errorMessage", "Devi selezionare un donatore.");
            return "redirect:/dashboard/admin/donazioni/save";
        }
        
        if (importo == null || importo <= 0) {
            redirectAttributes.addFlashAttribute("errorMessage", "L'importo deve essere maggiore di zero.");
            return "redirect:/dashboard/admin/donazioni/save";
        }
        
        if (metodoPagamento == null || metodoPagamento.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Devi selezionare un metodo di pagamento.");
            return "redirect:/dashboard/admin/donazioni/save";
        }
        
        // Crea nuova donazione
        Donazioni donazione = new Donazioni();
        donazione.setImporto(importo);
        donazione.setMetodo_pagamento(metodoPagamento);
        donazione.setNote(note);
        
        // Imposta la persona
        try {
            Utenti persona = utentiService.getUtenteById(personaId);
            if (persona == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Utente selezionato non trovato.");
                return "redirect:/dashboard/admin/donazioni/save";
            }
            donazione.setPersona(persona);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errore nel recupero dell'utente: " + e.getMessage());
            return "redirect:/dashboard/admin/donazioni/save";
        }
        
        // Imposta la data e ora
        try {
            if (dataDonazioneStr != null && !dataDonazioneStr.trim().isEmpty()) {
                // Parsea la data e imposta l'ora corrente
                LocalDate dataDate = LocalDate.parse(dataDonazioneStr);
                LocalDateTime dataOra = dataDate.atTime(LocalTime.now());
                donazione.setData_donazione(dataOra);
            } else {
                // Usa data e ora correnti
                donazione.setData_donazione(LocalDateTime.now());
            }
        } catch (Exception e) {
            // In caso di errore, usa data e ora correnti
            donazione.setData_donazione(LocalDateTime.now());
        }
        
        try {
            donazioniService.save(donazione);
            redirectAttributes.addFlashAttribute("successMessage", "Donazione registrata con successo!");
            System.out.println("Donazione salvata con successo!");
        } catch (Exception e) {
            System.err.println("Errore durante il salvataggio della donazione: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Errore durante il salvataggio: " + e.getMessage());
        }
        
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

    // Visualizza dettagli di una richiesta di adozione
    @GetMapping("/adozioni/dettagli/{id}")
    public String visualizzaDettagliAdozione(@PathVariable Integer id, Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        
        try {
            Adozioni adozione = adozioniService.getAdozioneById(id);
            if (adozione == null) {
                return "redirect:/dashboard/admin/adozioni";
            }
            
            // Forza il caricamento delle relazioni lazy per evitare LazyInitializationException
            if (adozione.getPersona() != null) {
                // Accesso alle proprietà per forzare il caricamento
                adozione.getPersona().getNome();
                adozione.getPersona().getCognome();
                adozione.getPersona().getEmail();
                adozione.getPersona().getNumero();
                adozione.getPersona().getCodiceFiscale();
            }
            
            if (adozione.getAnimale() != null) {
                adozione.getAnimale().getNome();
                if (adozione.getAnimale().getSpecie() != null) {
                    adozione.getAnimale().getSpecie().getNome();
                }
                if (adozione.getAnimale().getRazza() != null) {
                    adozione.getAnimale().getRazza().getNome();
                }
            }
            
            if (adozione.getStepAdozione() != null) {
                adozione.getStepAdozione().getNome_step();
            }
            
            model.addAttribute("adozione", adozione);
            return "dettagli_richiesta_adozione";
        } catch (Exception e) {
            System.err.println("Errore nel caricamento dettagli adozione: " + e.getMessage());
            return "redirect:/dashboard/admin/adozioni";
        }
    }

    @GetMapping("/adozioni/save")
    public String mostraFormCreazioneAdozione(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        model.addAttribute("adozione", new Adozioni());
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
        model.addAttribute("utenti", utentiService.getAllUtentiAttivi()); // Solo utenti attivi per le adozioni
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
        model.addAttribute("utenti", utentiService.getAllUtentiAttivi()); // Solo utenti attivi per le modifiche adozioni
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
        // Di default mostriamo solo utenti attivi, con opzione per vedere tutti
        boolean includiEliminati = session.getAttribute("mostraUtentiEliminati") != null && 
                                  (Boolean) session.getAttribute("mostraUtentiEliminati");
        
        if (includiEliminati) {
            model.addAttribute("utenti", utentiService.getAllUtenti()); // Tutti inclusi eliminati
            model.addAttribute("mostraTutti", true);
        } else {
            model.addAttribute("utenti", utentiService.getAllUtentiAttivi()); // Solo attivi
            model.addAttribute("mostraTutti", false);
        }
        
        return "dashboard_lista_utenti";
    }
    
    @PostMapping("/utenti/toggle-view")
    public String toggleViewUtenti(HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        Boolean current = (Boolean) session.getAttribute("mostraUtentiEliminati");
        session.setAttribute("mostraUtentiEliminati", current == null || !current);
        return "redirect:/dashboard/admin/utenti";
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
                                 @ModelAttribute("utente") Utenti utente,
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
        
        // Custom editor per LocalDateTime (per le donazioni)
        binder.registerCustomEditor(LocalDateTime.class, "data_donazione", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    // Il form HTML invia solo la data (yyyy-MM-dd), aggiungiamo l'ora corrente
                    LocalDate date = LocalDate.parse(text);
                    setValue(date.atTime(LocalTime.now()));
                }
            }
            @Override
            public String getAsText() {
                LocalDateTime dateTime = (LocalDateTime) getValue();
                return dateTime != null ? dateTime.toLocalDate().toString() : "";
            }
        });
        
        // Custom editor per convertire ID utente in oggetto Utenti
        binder.registerCustomEditor(Utenti.class, "persona", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    try {
                        Integer userId = Integer.valueOf(text);
                        Utenti utente = utentiService.getUtenteById(userId);
                        setValue(utente);
                    } catch (NumberFormatException e) {
                        setValue(null);
                    }
                }
            }
        });
        
        // Custom editor per convertire ID specie in oggetto Specie
        binder.registerCustomEditor(Specie.class, "specie", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    try {
                        Integer specieId = Integer.valueOf(text);
                        var specieOpt = specieService.getSpecieById(specieId);
                        setValue(specieOpt.orElse(null));
                    } catch (NumberFormatException e) {
                        setValue(null);
                    }
                }
            }
        });
        
        // Custom editor per convertire String in LocalDateTime
        binder.registerCustomEditor(LocalDateTime.class, "dataVisita", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    try {
                        // Supponiamo che la data venga ricevuta nel formato "dd/MM/yyyy HH:mm"
                        LocalDateTime dataOra = LocalDateTime.parse(text, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                        setValue(dataOra);
                    } catch (Exception e) {
                        setValue(null);
                    }
                }
            }
            @Override
            public String getAsText() {
                LocalDateTime value = (LocalDateTime) getValue();
                return (value != null) ? value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "";
            }
        });
    }

    @PostMapping("/utenti/delete/{id}")
    public String eliminaUtente(@PathVariable Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        
        try {
            // Controlla se l'utente ha donazioni o adozioni associate
            var utente = utentiService.getUtenteById(id);
            if (utente == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Utente non trovato.");
                return "redirect:/dashboard/admin/utenti";
            }
            
            // Verifica se è un admin che tenta di eliminare se stesso
            var currentUserId = session.getAttribute("userId");
            if (currentUserId != null && currentUserId.equals(id)) {
                redirectAttributes.addFlashAttribute("errorMessage", "Non puoi eliminare il tuo stesso account.");
                return "redirect:/dashboard/admin/utenti";
            }
            
            utentiService.deleteUtente(id); // Ora fa soft delete
            redirectAttributes.addFlashAttribute("successMessage", "Utente eliminato con successo (soft delete). Può essere ripristinato se necessario.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errore durante l'eliminazione: " + e.getMessage());
        }
        
        return "redirect:/dashboard/admin/utenti";
    }

    @GetMapping("/utenti/save")
    public String mostraFormCreazioneUtente(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        
        model.addAttribute("utente", new Utenti());
        return "creazione_utente_admin";
    }

    @PostMapping("/utenti/save")
    public String creaUtente(@Validated @ModelAttribute("utente") Utenti utente,
                            BindingResult bindingResult,
                            Model model,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        
        // Capitalizzazione e validazioni come nel controller Utenti
        utente.setNome(capitalizeEachWord(utente.getNome() != null ? utente.getNome().trim() : ""));
        utente.setCognome(capitalizeEachWord(utente.getCognome() != null ? utente.getCognome().trim() : ""));
        utente.setCodiceFiscale(utente.getCodiceFiscale() != null ? utente.getCodiceFiscale().toUpperCase().trim() : "");
        utente.setEmail(utente.getEmail() != null ? utente.getEmail().toLowerCase().trim() : "");
        
        // Verifica se email già esiste tra utenti attivi
        if (utentiService.existsActiveByEmail(utente.getEmail())) {
            bindingResult.rejectValue("email", "error.utente", "Esiste già un utente attivo con questa email.");
        }
        
        // Verifica se codice fiscale già esiste tra utenti attivi
        if (utentiService.existsActiveByCodiceFiscale(utente.getCodiceFiscale())) {
            bindingResult.rejectValue("codiceFiscale", "error.utente", "Esiste già un utente attivo con questo codice fiscale.");
        }
        
        if (bindingResult.hasErrors()) {
            return "creazione_utente_admin";
        }
        
        // Password di default se non specificata
        if (utente.getPassword() == null || utente.getPassword().trim().isEmpty()) {
            utente.setPassword("password123"); // Password temporanea
        }
        
        // Ruolo di default USER se non specificato
        if (utente.getRuolo() == null || utente.getRuolo().trim().isEmpty()) {
            utente.setRuolo("USER");
        }
        
        try {
            utentiService.createUtente(utente);
            redirectAttributes.addFlashAttribute("successMessage", "Utente creato con successo!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errore durante la creazione: " + e.getMessage());
        }
        
        return "redirect:/dashboard/admin/utenti";
    }
    
    @PostMapping("/utenti/restore/{id}")
    public String ripristinaUtente(@PathVariable Integer id, 
                                   HttpSession session, 
                                   RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        
        try {
            var utente = utentiService.getUtenteById(id);
            if (utente == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Utente non trovato.");
                return "redirect:/dashboard/admin/utenti";
            }
            
            if (utente.getAttivo()) {
                redirectAttributes.addFlashAttribute("errorMessage", "L'utente è già attivo.");
                return "redirect:/dashboard/admin/utenti";
            }
            
            utentiService.ripristinaUtente(id);
            redirectAttributes.addFlashAttribute("successMessage", "Utente ripristinato con successo.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errore durante il ripristino: " + e.getMessage());
        }
        
        return "redirect:/dashboard/admin/utenti";
    }

    @PostMapping("/utenti/hard-delete/{id}")
    public String eliminaUtenteFisicamente(@PathVariable Integer id, 
                                          HttpSession session, 
                                          RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            return "redirect:/";
        }
        
        try {
            var utente = utentiService.getUtenteById(id);
            if (utente == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Utente non trovato.");
                return "redirect:/dashboard/admin/utenti";
            }
            
            // Verifica se è un admin che tenta di eliminare se stesso
            var currentUserId = session.getAttribute("userId");
            if (currentUserId != null && currentUserId.equals(id)) {
                redirectAttributes.addFlashAttribute("errorMessage", "Non puoi eliminare il tuo stesso account.");
                return "redirect:/dashboard/admin/utenti";
            }
            
            // Elimina fisicamente solo se già soft-deleted
            if (utente.getAttivo()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Prima devi eliminare logicamente l'utente.");
                return "redirect:/dashboard/admin/utenti";
            }
            
            utentiService.eliminaUtenteFisicamente(id);
            redirectAttributes.addFlashAttribute("successMessage", "Utente eliminato definitivamente dal database.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errore durante l'eliminazione fisica: " + e.getMessage());
        }
        
        return "redirect:/dashboard/admin/utenti";
    }
}