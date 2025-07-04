package com.rifugio.rifugio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rifugio.rifugio.entities.Utenti;
import com.rifugio.rifugio.repos.UtentiRepo;
import com.rifugio.rifugio.services.UtentiService;

@Controller
@RequestMapping("/utenti")
public class UtentiController {

    @Autowired
    private UtentiService utentiService;

    @Autowired
    private UtentiRepo utentiRepo;

    // Endpoint: lista utenti (API)
    @GetMapping
    public String getAllUtenti(Model model) {
        List<Utenti> utenti = utentiService.getAllUtenti();
        model.addAttribute("utenti", utenti);
        return "lista_utenti"; // crea la view se serve, oppure restituisci JSON se vuoi @RestController
    }

    // Endpoint: form registrazione
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("utente", new Utenti());
        return "register";
    }

    // Endpoint: processa registrazione
    @PostMapping("/register")
    public String processRegister(@ModelAttribute Utenti utente, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        if (!utente.getPassword().equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Le password non coincidono.");
            return "register";
        }

        if (utente.getPassword().contains(" ") || confirmPassword.contains(" ")) {
            model.addAttribute("errorMessage", "La password non può contenere spazi.");
            return "register";
        }

        utente.setRuolo("USER");
        utente.setCodiceFiscale(utente.getCodiceFiscale().toUpperCase().trim());
        utente.setNome(capitalizeEachWord(utente.getNome().trim()));
        utente.setCognome(capitalizeEachWord(utente.getCognome().trim()));
        utente.setPassword(confirmPassword.trim());
        utentiRepo.save(utente);

        return "redirect:/utenti/login?registered=true";
    }

    // Endpoint: form login
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "registered", required = false) String registered, Model model) {
        if (registered != null) {
            model.addAttribute("successMessage", "Registrazione avvenuta con successo! Ora puoi effettuare il login.");
        }
        return "login";
    }

    // Endpoint: processa login
    @PostMapping("/login")
    public String doLogin(@RequestParam("email") String Email,
                         @RequestParam("password") String password,
                         Model model,
                         jakarta.servlet.http.HttpSession session) {
        Optional<Utenti> utenteOpt = utentiRepo.findAll().stream()
                .filter(u -> (u.getEmail().equalsIgnoreCase(Email))
                        && u.getPassword().equals(password))
                .findFirst();
        if (utenteOpt.isPresent()) {
            Utenti utente = utenteOpt.get();
            model.addAttribute("utente", utente);
            // Salva nome e iniziali in sessione
            String initials = getUserInitials(utente);
            session.setAttribute("userInitials", initials);
            session.setAttribute("userFullName", utente.getNome() + " " + utente.getCognome());
            session.setAttribute("userRuolo", utente.getRuolo());
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "Credenziali non valide. Riprova.");
            return "login";
        }
    }

    // Utility per ottenere le iniziali dell'utente
    private String getUserInitials(Utenti utente) {
        String nome = utente.getNome() != null ? utente.getNome().trim() : "";
        String cognome = utente.getCognome() != null ? utente.getCognome().trim() : "";
        String initialNome = nome.isEmpty() ? "" : nome.substring(0, 1).toUpperCase();
        String initialCognome = cognome.isEmpty() ? "" : cognome.substring(0, 1).toUpperCase();
        return initialNome + initialCognome;
    }



    // Utility per capitalizzare
    private String capitalizeEachWord(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return java.util.Arrays.stream(str.trim().toLowerCase().split("\\s+"))
            .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
            .reduce((w1, w2) -> w1 + " " + w2)
            .orElse("");
    }

}
