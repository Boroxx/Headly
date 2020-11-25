package com.headly.Headly.controller;

import com.headly.Headly.ErrorHandling.TemplateLogger;
import com.headly.Headly.models.ConfirmationToken;
import com.headly.Headly.models.User;
import com.headly.Headly.repos.ConformationTokenRepository;
import com.headly.Headly.services.MailSenderService;
import com.headly.Headly.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

  @Value("${spring.mail.username}")
  private String javamailsender_mail;


  @Autowired
  RegistrationService registrationService;

  @Autowired
  ConformationTokenRepository conformationTokenRepository;

  @Autowired
  MailSenderService mailSenderService;


  @GetMapping("/companyregistration")
  public String companyregistration(Model model){
    model.addAttribute("user",new User());
    return"registration";
  }


  @GetMapping("/registrationverified")
  public String verified(Model model){
    return "/RegistrationVerified";
  }

  @GetMapping("/registrationfailed")
  public String failed(Model model){
    return "/RegistratioFailed";
  }

  @GetMapping("/registrationConfirm")
  public String confirmToken(Model model,@RequestParam("token") String confirmationToken){
    ConfirmationToken confToken =conformationTokenRepository.findByConfirmationToken(confirmationToken);

    if(confToken!=null){
      User user = registrationService.findUserById(confToken.getUser().getEmail());
      user.setEnabled(true);
      registrationService.saveUser(user);
      return "redirect:/registrationverified";

    }else{
      return "redirect:/registrationfailed";
    }
  }


  @PostMapping("/companyregistration")
  public String makePost(@ModelAttribute User user, RedirectAttributes redirectAttributes, HttpServletRequest request){

    if(registrationService.findUserById(user.getEmail())!=null){
      redirectAttributes.addFlashAttribute("error","User vergeben");
      return"redirect:/companyregistration";

    }else {
      System.out.println(javamailsender_mail);
      String appurl =request.getContextPath();
      registrationService.registerNewAccount(user);
      ConfirmationToken confirmationToken = new ConfirmationToken(user);
      conformationTokenRepository.save(confirmationToken);
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setTo(user.getEmail());
      mailMessage.setSubject("Headly - Registrierung vervollständigen!");
      mailMessage.setFrom(javamailsender_mail);
      mailMessage.setText("Um deinen Account freizuschalten klicke den folgenden Link: " + appurl + "/registrationConfirm?token=" +confirmationToken.getConfirmationToken());
      redirectAttributes.addFlashAttribute("success","Registration war erfolgreich. Schaue in deinen Posteingang um deinen Account zu verifizieren.");
      mailSenderService.sendMail(mailMessage);


      return"redirect:/companyregistration";
    }

  }
}
