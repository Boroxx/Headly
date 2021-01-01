package com.headly.Headly.controller;

import com.headly.Headly.ErrorHandling.TemplateLogger;
import com.headly.Headly.models.ConfirmationToken;
import com.headly.Headly.models.User;
import com.headly.Headly.repos.ConformationTokenRepository;
import com.headly.Headly.services.MailSenderService;
import com.headly.Headly.services.RegistrationService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  Logger logger = LoggerFactory.getLogger(RegistrationController.class);


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

  @GetMapping("/applicantregistration")
  public String applicantregistration(Model model){
    model.addAttribute("user",new User());
    return"registrationApplicant";
  }


  @GetMapping("/registrationverified")
  public String verified(Model model){
    return "RegistrationVerified";
  }

  @GetMapping("/registrationfailed")
  public String failed(Model model){
    return "RegistratioFailed";
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
      logger.error("Konnte ConfirmationToken nicht in Datenbank finden...");
      return "redirect:/registrationfailed";
    }
  }


  @PostMapping("/companyregistration")
  public String makePostApplicant(@ModelAttribute User user, RedirectAttributes redirectAttributes, HttpServletRequest request){

    if(registrationService.findUserById(user.getEmail())!=null){
      redirectAttributes.addFlashAttribute("error","User vergeben");
      logger.info("Useraccountname ist bereits in Datenbank vorhanden.");
      return"redirect:/companyregistration";

    }else {
      System.out.println(javamailsender_mail);
      String appurl =request.getServerName();
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
      logger.info("Verification Email successfully send");

      return"redirect:/companyregistration";
    }

  }

  @PostMapping("/applicantregistration")
  public String makePost(@ModelAttribute User user, RedirectAttributes redirectAttributes, HttpServletRequest request){

    if(registrationService.findUserById(user.getEmail())!=null){
      redirectAttributes.addFlashAttribute("error","User vergeben");
      logger.info("Useraccountname ist bereits in Datenbank vorhanden.");
      return"redirect:/companyregistration";

    }else {
      System.out.println(javamailsender_mail);
      String appurl =request.getServerName();
      registrationService.registerNewAccount(user);
      ConfirmationToken confirmationToken = new ConfirmationToken(user);
      conformationTokenRepository.save(confirmationToken);
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setTo(user.getEmail());
      mailMessage.setSubject("Headly - Registrierung vervollständigen!");
      mailMessage.setFrom(javamailsender_mail);
      mailMessage.setText("Um deinen Account freizuschalten klicke den folgenden Link: " + appurl + "/registrationConfirm?token=" +confirmationToken.getConfirmationToken()+"\n Bitte antworten Sie nicht auf diese Email" );
      redirectAttributes.addFlashAttribute("success","Registration war erfolgreich. Schaue in deinen Posteingang um deinen Account zu verifizieren.");
      mailSenderService.sendMail(mailMessage);
      logger.info("Verification Email successfully send");

      return"redirect:/applicantregistration";
    }

  }


}
