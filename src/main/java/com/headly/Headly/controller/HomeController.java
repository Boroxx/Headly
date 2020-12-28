package com.headly.Headly.controller;

import com.headly.Headly.models.Jobpost;
import com.headly.Headly.models.Profession;
import com.headly.Headly.models.User;
import com.headly.Headly.services.PostingService;
import com.headly.Headly.services.ProfessionService;
import com.headly.Headly.services.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

  @Autowired
  PostingService postingService;

  @Autowired
  RegistrationService registrationService;

  @Autowired
  ProfessionService professionService;
  Logger logger = LoggerFactory.getLogger(HomeController.class);


  @GetMapping("/")
  public String home(Model model){
    List<Jobpost> jobposts = postingService.loadAllJobs();
    Collections.shuffle(jobposts);
    List<Jobpost> firstsixjobs = jobposts.stream().limit(6).collect(Collectors.toList());

    model.addAttribute("jobposts", firstsixjobs);

    return "index";
  }

  @GetMapping("/applicants")
  public String applicants(Model model){
    return "applicants";
  }


  @GetMapping("/addausschreibungen")
  public String admin(Model model){
    List<Profession> professions = professionService.getAllProfessions();
    model.addAttribute("jobPost", new Jobpost());
    model.addAttribute("professions", professions);
    return "addausschreibung";
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
     if(user!=null){
       int id = user.getId();
       List<Jobpost> jobPosts = postingService.loadAllJobsById(id);
       model.addAttribute("jobposts",jobPosts);
       model.addAttribute("contactperson", user.getContactperson());
     }else{
       logger.error("User not not found by id: " + username);
     }

    return "ausschreibungen";

  }

}

