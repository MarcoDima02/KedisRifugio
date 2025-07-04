package com.rifugio.rifugio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestPageController {
    @GetMapping("/test-upload")
    public String testUpload() {
        return "test_upload_immagine";
    }

    @GetMapping("/test-animali")
    public String testAnimali() {

}