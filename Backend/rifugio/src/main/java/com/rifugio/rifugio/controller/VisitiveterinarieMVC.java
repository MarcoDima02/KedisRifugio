package com.rifugio.rifugio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rifugio.rifugio.services.VisiteveterinarieService;

@Controller
public class VisitiveterinarieMVC {
    @Autowired
    private VisiteveterinarieService service;

    @GetMapping("visiteveterinarie")
    public String getVisiteVeterinarie(Model m) {
        m.addAttribute("visite", service.getVisiteVeterinarie());
        return "visiteveterinarie";
    }
}
