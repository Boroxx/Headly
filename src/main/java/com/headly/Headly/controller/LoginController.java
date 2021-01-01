package com.headly.Headly.controller;


import com.headly.Headly.dto.MailDto;
import com.headly.Headly.models.User;
import com.headly.Headly.services.MailSenderService;
import com.headly.Headly.services.RegistrationService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {


    @Value("${spring.mail.username}")
    private String javamailsender_mail;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    MailSenderService mailSenderService;


    @GetMapping("/login")
    public String login(){
        return "login";

    }

    @GetMapping("/loginredirect")
    public String redirection(){
            return "redirect:/";
    }

    @GetMapping("/loginerror")
    public String errorlogin(Model model, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error","Login Fehlgeschlagen! Benutzerdaten falsch!");
        return "redirect:/login";
    }


    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("/forgot-password")
    public String forgotpassword(Model model){
        model.addAttribute("mailDto",new MailDto());
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPost(@ModelAttribute MailDto mailDto, RedirectAttributes redirectAttributes){
        System.out.println(mailDto.getMail());
        User user = registrationService.findUserById(mailDto.getMail());
        if(user==null){
            redirectAttributes.addFlashAttribute("error","Login Fehlgeschlagen! Benutzerdaten falsch!");
            return "redirect:/forgot-password";
        }else{
            /*gen random pass*/
            String mpass = random();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String pass =  encoder.encode(mpass);
            user.setPassword(pass);
            registrationService.saveUser(user);

            // send mail with new pass
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Headly - Neues Passwort!");
            mailMessage.setFrom(javamailsender_mail);
            mailMessage.setText("Hallo,  hier dein neues passwort: " + mpass + "\n Dein Headly-Team \n Wende dich bei fragen an: team@headly.de");
            redirectAttributes.addFlashAttribute("success","Neues Passwort wurde versendet");
            mailSenderService.sendMail(mailMessage);
            return "redirect:/forgot-password";

        }

    }

    public String random(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
        return RandomStringUtils.random( 12, characters );


    }
}
