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
        // Qui puoi aggiungere controlli su email/username duplicati

        //Imposta il ruolo dell'utente
        utente.setRuolo("USER");
        utentiRepo.save(utente);
        // Redirect alla pagina di login con messaggio di successo
        return "redirect:/login?registered=true";
    }


}