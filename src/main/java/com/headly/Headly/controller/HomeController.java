package com.headly.Headly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


  @GetMapping("/")
  public String home(){
    return "index.html";
  }

  @GetMapping("/admin")
  public String admin(){
    return "admin.html";
  }
  @GetMapping("/logout")
  public String logout(){
    return "logout.html";
  }
}

