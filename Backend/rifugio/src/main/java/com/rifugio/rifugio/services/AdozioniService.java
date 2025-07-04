package com.rifugio.rifugio.services;

import java.util.List;
import com.rifugio.rifugio.entities.Adozioni;

public interface AdozioniService {
    List<Adozioni> getAllAdozioni();
    Adozioni getAdozioneById(Integer id);
    Adozioni createAdozione(Adozioni adozione);
    Adozioni updateAdozione(Integer id, Adozioni adozione);
}
