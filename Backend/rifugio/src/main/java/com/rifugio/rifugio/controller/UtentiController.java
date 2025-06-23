package com.rifugio.rifugio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rifugio.rifugio.entities.Utenti;
import com.rifugio.rifugio.services.UtentiService;

@RestController
@RequestMapping("/utenti")
public class UtentiController {

    @Autowired
    private UtentiService utentiService;

    // Define endpoints here, e.g., to get all users
    @GetMapping
    public List<Utenti> getAllUtenti() {
        return utentiService.getAllUtenti();
    }

    // Add more methods as needed for user management
}
