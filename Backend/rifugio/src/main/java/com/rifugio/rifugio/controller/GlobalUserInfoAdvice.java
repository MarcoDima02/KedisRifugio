package com.rifugio.rifugio.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalUserInfoAdvice {
    @ModelAttribute
    public void addUserInfoToModel(HttpSession session, Model model) {
        Object initials = session.getAttribute("userInitials");
        Object fullName = session.getAttribute("userFullName");
        if (initials != null) {
            model.addAttribute("userInitials", initials);
        }
        if (fullName != null) {
            model.addAttribute("userFullName", fullName);
        }
    }
}
