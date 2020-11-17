package com.headly.Headly.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";

    }




    @GetMapping("/loginerror")
    public String errorlogin(Model model, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error","Login Fehlgeschlagen! Benutzerdaten falsch!");
        return "redirect:/login";
    }
}
