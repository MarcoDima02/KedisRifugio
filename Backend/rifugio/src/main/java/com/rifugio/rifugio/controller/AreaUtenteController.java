package com.rifugio.rifugio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AreaUtenteController {
    @GetMapping("/area-utente")
    public String areaUtente() {
        return "area_utente";
    }
}
