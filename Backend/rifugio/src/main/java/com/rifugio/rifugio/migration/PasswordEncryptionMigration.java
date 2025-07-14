package com.rifugio.rifugio.migration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rifugio.rifugio.entities.Utenti;
import com.rifugio.rifugio.repos.UtentiRepo;

/**
 * Migrazione per criptare le password esistenti nel database
 * Viene eseguita solo al primo avvio dopo l'implementazione della crittografia
 */
@Component
public class PasswordEncryptionMigration implements ApplicationRunner {

    @Autowired
    private UtentiRepo utentiRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Avvio migrazione crittografia password...");
        
        List<Utenti> utenti = utentiRepo.findAll();
        int passwordMigrate = 0;
        
        for (Utenti utente : utenti) {
            String password = utente.getPassword();
            
            // Controlla se la password è già criptata (BCrypt inizia sempre con $2a$, $2b$, o $2y$)
            if (password != null && !password.startsWith("$2")) {
                System.out.println("Criptando password per utente: " + utente.getEmail());
                String encodedPassword = passwordEncoder.encode(password);
                utente.setPassword(encodedPassword);
                utentiRepo.save(utente);
                passwordMigrate++;
            }
        }
        
        if (passwordMigrate > 0) {
            System.out.println("Migrazione completata! Criptate " + passwordMigrate + " password.");
        } else {
            System.out.println("Nessuna password da migrare. Tutte le password sono già criptate.");
        }
    }
}
