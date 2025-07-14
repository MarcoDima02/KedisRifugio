package com.rifugio.rifugio.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DateConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateConverter());
        registry.addConverter(new StringToLocalTimeConverter());
    }

    public static class StringToLocalDateConverter implements Converter<String, LocalDate> {
        @Override
        public LocalDate convert(String source) {
            if (source == null || source.isEmpty()) {
                return null;
            }
            try {
                // Formato ISO standard yyyy-MM-dd che viene utilizzato dai campi input type="date"
                return LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (Exception e) {
                // Fallback per altri formati comuni
                try {
                    return LocalDate.parse(source, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                } catch (Exception e2) {
                    try {
                        return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    } catch (Exception e3) {
                        throw new IllegalArgumentException("Formato data non valido: " + source);
                    }
                }
            }
        }
    }

    public static class StringToLocalTimeConverter implements Converter<String, LocalTime> {
        @Override
        public LocalTime convert(String source) {
            if (source == null || source.isEmpty()) {
                return null;
            }
            try {
                // Formato ISO standard HH:mm che viene utilizzato dai campi input type="time"
                return LocalTime.parse(source, DateTimeFormatter.ISO_LOCAL_TIME);
            } catch (Exception e) {
                // Fallback per formato HH:mm
                try {
                    return LocalTime.parse(source, DateTimeFormatter.ofPattern("HH:mm"));
                } catch (Exception e2) {
                    throw new IllegalArgumentException("Formato ora non valido: " + source);
                }
            }
        }
    }
}
