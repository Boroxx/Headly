package com.headly.Headly.controller;

import com.headly.Headly.ErrorHandling.TemplateLogger;
import com.headly.Headly.dto.ApplicationDto;
import com.headly.Headly.models.ApplicationModel;
import com.headly.Headly.models.Jobpost;
import com.headly.Headly.models.User;
import com.headly.Headly.services.ApplicationModelService;
import com.headly.Headly.services.PostingService;
import com.headly.Headly.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

  @Autowired
  PostingService postingService;

  @Autowired
  RegistrationService registrationService;

  @Autowired
  ApplicationModelService applicationModelService;



  @GetMapping("/authentication/{jobid}")
  public String authentication(@PathVariable String jobid,Model model){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    System.out.println(auth);

    if(auth instanceof AnonymousAuthenticationToken){
      return "redirect:/companyregistration";
    }else {
      String username = ((UserDetails)auth.getPrincipal()).getUsername();
      User user = registrationService.findUserById(username);
      if(  user.getRole().equals("ROLE_BEWERBER")) return "redirect:/upload/" + Integer.parseInt(jobid);
    }

    return "index";


  }

  @PostMapping("/upload")
  public String singleFileUpload(@ModelAttribute("applicationDto") ApplicationDto applicationDto) throws IOException {
    System.out.println("POST");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = ((UserDetails)auth.getPrincipal()).getUsername();
    User user = registrationService.findUserById(username);


    if(user!=null && applicationDto!=null){
      applicationModelService.registerApplication(user,applicationDto,applicationDto.getJobid());
      return "redirect:/upload/"+ applicationDto.getJobid();
    }

    return "redirect:/upload" ;

  }

  @GetMapping("/upload/{jobid}")
  public String uploadStatus(@PathVariable String jobid,Model model) {
    Jobpost jobpost = postingService.findById(Integer.parseInt(jobid));
    if(jobpost!=null){
      model.addAttribute("jobpost",jobpost);
    }
    model.addAttribute("applicationDto", new ApplicationDto());
    return "upload";
  }

}
