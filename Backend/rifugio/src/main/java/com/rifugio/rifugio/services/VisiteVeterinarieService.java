package com.rifugio.rifugio.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rifugio.rifugio.entities.VisiteVeterinarie;

@Service
public interface VisiteVeterinarieService {

    List<VisiteVeterinarie> getAllVisiteVeterinarie();



}
