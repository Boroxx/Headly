package com.headly.Headly.controller;


import com.headly.Headly.models.User;
import com.headly.Headly.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    RegistrationService registrationService;

    @GetMapping("/login")
    public String login(){
        return "login";

    }

    @GetMapping("/loginredirect")
    public String redirection(){
            return "redirect:/";
    }

    @GetMapping("/loginerror")
    public String errorlogin(Model model, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error","Login Fehlgeschlagen! Benutzerdaten falsch!");
        return "redirect:/login";
    }


    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

}
