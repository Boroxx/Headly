package com.headly.Headly.controller;

import com.headly.Headly.models.Jobpost;
import com.headly.Headly.models.Profession;
import com.headly.Headly.models.User;
import com.headly.Headly.services.PostingService;
import com.headly.Headly.services.ProfessionService;
import com.headly.Headly.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

  @Autowired
  RegistrationService registrationService;

  @Autowired
  ProfessionService professionService;


  @GetMapping("/")
  public String home(Model model){
    List<Jobpost> jobposts = postingService.loadAllJobs();
    model.addAttribute("jobposts", jobposts);
    return "index";
  }

  @GetMapping("/applicants")
  public String applicants(Model model){
    return "applicants";
  }
  @GetMapping("/admin")
  public String admin(Model model){
    List<Profession> professions = professionService.getAllProfessions();
    model.addAttribute("jobPost", new Jobpost());
    model.addAttribute("professions", professions);
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


  @GetMapping("/ausschreibungen")
  public String ausschreibungen(Model model){


    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = ((UserDetails)auth.getPrincipal()).getUsername();

     User user = registrationService.findUserById(username);

     int id = user.getId();
     List<Jobpost> jobPosts = postingService.loadAllJobsById(id);
     model.addAttribute("jobposts",jobPosts);
     model.addAttribute("contactperson", user.getContactperson());





    return "ausschreibungen";

  }

}

