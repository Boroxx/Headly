package com.headly.Headly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

  @Autowired
  private JavaMailSender javaMailSender;


  @Async
  public void sendMail(SimpleMailMessage simpleMailMessage){
    javaMailSender.send(simpleMailMessage);

  }

}
