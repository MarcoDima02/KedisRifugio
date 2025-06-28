package com.rifugio.rifugio.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rifugio.rifugio.entities.Immagine;
import com.rifugio.rifugio.services.ImmagineService;

@RestController
@RequestMapping("/immagini")
public class ImmagineController {

    @Autowired
    private ImmagineService immagineService;

    // Ottieni tutte le immagini (solo metadati)
    @GetMapping
    public List<ImmagineResponse> getAllImmagini() {
        return immagineService.getAllImmagini().stream()
                .map(this::mapToImmagineResponse)
                .collect(Collectors.toList());
    }
    
    // Ottieni immagine specifica per ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getImmagineById(@PathVariable int id) {
        Optional<Immagine> img = immagineService.getImmagineById(id);
        return img.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Ottieni tutte le immagini di un animale specifico (solo metadati)
    @GetMapping("/animale/{idAnimale}")
    public ResponseEntity<List<ImmagineResponse>> getImmaginiByAnimale(@PathVariable int idAnimale) {
        List<ImmagineResponse> immagini = immagineService.getImmaginiByAnimale(idAnimale).stream()
                .map(this::mapToImmagineResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(immagini);
    }
    
    // Carica una nuova immagine
    @PostMapping(value = "/carica")
    public ResponseEntity<?> caricaImmagine(@RequestParam("file") MultipartFile file, @RequestParam("idAnimale") int idAnimale) {
        try {
            Immagine img = immagineService.storeImmagine(file, idAnimale);
            return ResponseEntity.ok(img);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // Elimina un'immagine
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteImmagine(@PathVariable int id) {
        try {
            immagineService.deleteImmagine(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Ottieni i dati di un'immagine come file
    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getImmagineFile(@PathVariable int id) {
        Optional<Immagine> img = immagineService.getImmagineById(id);
        if (img.isPresent()) {
            return ResponseEntity.ok()
                .header("Content-Type", img.get().getTipo())
                .body(img.get().getDati());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Classe di risposta per le immagini (senza i dati binari)
    private static class ImmagineResponse {
        private int id;
        private String nome;
        private String tipo;
        private String url;
        private String dataCaricamento;
        private int idAnimale;
        
        public ImmagineResponse(int id, String nome, String tipo, String url, String dataCaricamento, int idAnimale) {
            this.id = id;
            this.nome = nome;
            this.tipo = tipo;
            this.url = url;
            this.dataCaricamento = dataCaricamento;
            this.idAnimale = idAnimale;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getTipo() {
            return tipo;
        }

        public String getUrl() {
            return url;
        }

        public String getDataCaricamento() {
            return dataCaricamento;
        }

        public int getIdAnimale() {
            return idAnimale;
        }
    }
    
    // Metodo per mappare un'entità Immagine a ImmagineResponse
    private ImmagineResponse mapToImmagineResponse(Immagine immagine) {
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/immagini/")
                .path(String.valueOf(immagine.getId_immagine()))
                .toUriString();
        
        return new ImmagineResponse(
                immagine.getId_immagine(),
                immagine.getNome(),
                immagine.getTipo(),
                fileDownloadUri,
                immagine.getData_caricamento().toString(),
                immagine.getAnimale() != null ? immagine.getAnimale().getIdAnimale() : 0
        );
    }
}