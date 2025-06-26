package com.rifugio.rifugio.controller;

import com.rifugio.rifugio.services.AnagraficaAnimaleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {

    @Autowired
    AnagraficaAnimaleServiceImpl anagraficaAnimaleService;

    @GetMapping("/")
    public String dashboardAnimali(Model model) {
        model.addAttribute("animali", anagraficaAnimaleService.getAllAnagraficaAnimali());
        return "dashboard_lista_animali";
    }
    


}
