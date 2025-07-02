package com.rifugio.rifugio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rifugio.rifugio.entities.Utenti;
import com.rifugio.rifugio.repos.UtentiRepo;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UtentiRepo utentiRepo;

    public RegisterController(UtentiRepo utentiRepo) {
        this.utentiRepo = utentiRepo;
    }

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("utente", new Utenti());
        return "register";
    }

    @PostMapping
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

        return "login";
    }

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