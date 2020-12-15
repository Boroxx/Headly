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

@Controller
public class ApplicantController {

    @Autowired
    RegistrationService registrationService;

    @GetMapping("/myApplciantProfile")
    public String myprofile(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)auth.getPrincipal()).getUsername();
        User user = registrationService.findUserById(username);
        model.addAttribute("user",user);
        return "myApplicantProfile";

    }
}
