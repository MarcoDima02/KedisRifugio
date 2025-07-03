package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifugio.rifugio.entities.Donazioni;
import com.rifugio.rifugio.services.DonazioniService;

@Controller
@RequestMapping("/donazioni")
public class DonazioniController {

    @Autowired
    private DonazioniService donazioniService;

    @GetMapping
    public String getAllDonazioni(Model model) {
        List<Donazioni> donazioni = donazioniService.getAllDonazioni();
        model.addAttribute("donazioni", donazioni);
        return "dashboard_lista_donazioni";
    }

}
