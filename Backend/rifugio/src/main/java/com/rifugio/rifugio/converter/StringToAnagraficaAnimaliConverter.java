package com.rifugio.rifugio.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rifugio.rifugio.entities.AnagraficaAnimali;
import com.rifugio.rifugio.services.AnagraficaAnimaliServiceImpl;

@Component
public class StringToAnagraficaAnimaliConverter implements Converter<String, AnagraficaAnimali> {

    @Autowired
    private AnagraficaAnimaliServiceImpl anagraficaAnimaliService;

    @Override
    public AnagraficaAnimali convert(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        try {
            Integer idInt = Integer.valueOf(id);
            return anagraficaAnimaliService.getByIdAnagraficaAnimali(idInt);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
