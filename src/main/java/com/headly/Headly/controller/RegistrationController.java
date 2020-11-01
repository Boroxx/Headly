package com.headly.Headly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {


  @GetMapping("/companyregistration")
  public String companyregistration(){
    return"registration";
  }
}
