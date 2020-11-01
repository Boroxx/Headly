package com.headly.Headly.controller;

import com.headly.Headly.models.Jobpost;
import com.headly.Headly.services.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

  @Autowired
  PostingService postingService;

  @GetMapping("/")
  public String home(Model model){
    List<Jobpost> jobposts = postingService.loadAllJobs();
    model.addAttribute("jobposts", jobposts);
    return "index";
  }

  @GetMapping("/admin")
  public String admin(){
    return "admin";
  }

  @GetMapping("/logout")
  public String logout(){
    return "logout";
  }

  @GetMapping("/company")
  public String company(){
    return"company";

  }
  @GetMapping("/login")
  public String login(){
    return "login";

  }
  @PostMapping("/admin/postJob")
  public String makePost(@ModelAttribute Jobpost jobPost){
    postingService.registerPost(jobPost);
    System.out.println("Hello");

    return"redirect:/admin";
  }
}

