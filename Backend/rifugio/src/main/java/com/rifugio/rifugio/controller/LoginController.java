package com.rifugio.rifugio.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rifugio.rifugio.entities.Utenti;
import com.rifugio.rifugio.repos.UtentiRepo;

@Controller
public class LoginController {
    @Autowired
    private UtentiRepo utentiRepo;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("email") String Email,
                         @RequestParam("password") String password,
                         Model model) {
        Optional<Utenti> utenteOpt = utentiRepo.findAll().stream()
                .filter(u -> (u.getEmail().equalsIgnoreCase(Email) || u.getEmail().equalsIgnoreCase(Email))
                        && u.getPassword().equals(password))
                .findFirst();
        if (utenteOpt.isPresent()) {
            // Login riuscito, reindirizza all'area utente
            model.addAttribute("utente", utenteOpt.get());
            return "redirect:/area-utente";
        } else {
            // Login fallito, mostra errore
            model.addAttribute("errorMessage", "Credenziali non valide. Riprova.");
            return "login";
        }
    }
}
