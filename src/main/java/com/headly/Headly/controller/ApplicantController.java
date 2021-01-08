package com.headly.Headly.controller;


import com.headly.Headly.dto.ApplicationOverviewDto;
import com.headly.Headly.dto.LebenslaufDto;
import com.headly.Headly.models.ApplicationModel;
import com.headly.Headly.models.Lebenslauf;
import com.headly.Headly.models.User;
import com.headly.Headly.repos.UploadLebenslaufRepository;
import com.headly.Headly.services.ApplicationModelService;
import com.headly.Headly.services.RegistrationService;
import com.headly.Headly.services.UploadLebenslaufService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicantController {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    ApplicationModelService applicationModelService;

    @Autowired
    UploadLebenslaufService uploadLebenslaufService;

    Logger logger = LoggerFactory.getLogger(ApplicantController.class);

    @GetMapping("/myApplciantProfile")
    public String myprofile(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User usercore = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        logger.info(usercore.getUsername());
        String username = usercore.getUsername();
        User user = registrationService.findUserById(username);
        model.addAttribute("user",user);
        return "myApplicantProfile";

    }

    @GetMapping("/myApplication")
    public String myapplication(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        org.springframework.security.core.userdetails.User usercore = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        logger.info(usercore.getUsername());
        String username = usercore.getUsername();
        User user = registrationService.findUserById(username);
        ApplicationOverviewDto applicationOverviewDto = applicationModelService.loadApplicationOverviewDtoByEmail(user);
        model.addAttribute("applicationDto", applicationOverviewDto);
        model.addAttribute("lebenslaufDto", new LebenslaufDto());
        model.addAttribute("isUploaded", false);
        if(uploadLebenslaufService.findLebenslaufByUserId(user) !=null){
            model.addAttribute("isUploaded", true);
        }


        return "meinebewerbung";
    }

    @PostMapping("/myApplication")
    public String postLebenslauf(@ModelAttribute LebenslaufDto lebenslaufDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        org.springframework.security.core.userdetails.User usercore = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        logger.info(usercore.getUsername());
        String username = usercore.getUsername();
        User user = registrationService.findUserById(username);
        uploadLebenslaufService.registerLebenslauf(user,lebenslaufDto.getMultipartFile());
        return "redirect:/myApplication";


    }

    @Transactional
    @GetMapping("/applicationpdf")
    public ResponseEntity<byte[]> getPDF() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "Lebenslauf.pdf";
        headers.add("content-disposition", "inline;filename=" + filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        org.springframework.security.core.userdetails.User usercore = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        logger.info(usercore.getUsername());
        String username = usercore.getUsername();
        User user = registrationService.findUserById(username);
        Lebenslauf lebenslauf= uploadLebenslaufService.findLebenslaufByUserId(user);
        if(lebenslauf.getLebenslauf_pdf()==null){
            logger.error("Konnte PDF-File mit Lebenslauf nicht in Datenbank finden");
            return new ResponseEntity<byte[]>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<byte[]>(lebenslauf.getLebenslauf_pdf(), headers, HttpStatus.OK);
        }

    }
    @GetMapping("/deleteLebenslauf")
    public String deleteLebenslauf(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        org.springframework.security.core.userdetails.User usercore = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        logger.info(usercore.getUsername());
        String username = usercore.getUsername();
        User user = registrationService.findUserById(username);
        uploadLebenslaufService.deleteLebenslauf(user);
        return "redirect:/myApplication";
    }
}
