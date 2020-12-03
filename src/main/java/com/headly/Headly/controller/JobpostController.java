package com.headly.Headly.controller;

import com.headly.Headly.models.Jobpost;
import com.headly.Headly.services.PostingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JobpostController {

  @Autowired
  PostingService postingService;

  Logger logger = LoggerFactory.getLogger(HomeController.class);

  @GetMapping("/jobdetails/{jobid}")
  public String jobdetails(@PathVariable String jobid, Model model){
    Jobpost jobpost = postingService.findById(Integer.parseInt(jobid));
    if(jobpost!=null){
      model.addAttribute("jobpost",jobpost);
      return"jobdetails";
    }else{
      logger.error("Konnte Job nicht anhand von Job-ID finden");
    }
    return "redirect:/error";
  }

  @PostMapping("/admin/postJob")
  public String makePost(@ModelAttribute Jobpost jobPost){
    System.out.println(jobPost.getDescription());
    postingService.registerPost(jobPost);


    return"redirect:/ausschreibungen";
  }

  @GetMapping()
  public String roleCheck(){
    return "";
  }
}
