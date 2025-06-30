package com.rifugio.rifugio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rifugio.rifugio.services.AnagraficaAnimaleServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private AnagraficaAnimaleServiceImpl anagraficaAnimaleService;

    @GetMapping("/animali")
    public String getAnimali(@RequestParam String param, Model model) {
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
        return "lista_animali";
    }
    

}
