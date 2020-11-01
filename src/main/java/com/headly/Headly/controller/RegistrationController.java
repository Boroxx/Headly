package com.headly.Headly.controller;

import com.headly.Headly.models.Jobpost;
import com.headly.Headly.models.User;
import com.headly.Headly.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

  @Autowired
  RegistrationService registrationService;

  @GetMapping("/companyregistration")
  public String companyregistration(Model model){
    model.addAttribute("user",new User());
    return"registration";
  }


  @PostMapping("/companyregistration")
  public String makePost(@ModelAttribute User user){
    registrationService.registerNewAccount(user);

    return"redirect:/";
  }
}
