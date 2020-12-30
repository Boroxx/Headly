package com.headly.Headly.controller;


import com.headly.Headly.dto.ApplicationOverviewDto;
import com.headly.Headly.models.User;
import com.headly.Headly.services.ApplicationModelService;
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
    @Autowired
    ApplicationModelService applicationModelService;

    @GetMapping("/myApplciantProfile")
    public String myprofile(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User usercore = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        String username = usercore.getUsername();
        User user = registrationService.findUserById(username);
        model.addAttribute("user",user);
        return "myApplicantProfile";

    }

    @GetMapping("/myApplicaiton")
    public String myapplication(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        org.springframework.security.core.userdetails.User usercore = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        String username = usercore.getUsername();
        User user = registrationService.findUserById(username);
        ApplicationOverviewDto applicationOverviewDto = applicationModelService.loadApplicationOverviewDtoByEmail(user);
        model.addAttribute("applicationDto", applicationOverviewDto);
        return "meinebewerbung";
    }
}
