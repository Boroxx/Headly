package com.headly.Headly.services;


import com.headly.Headly.models.User;
import com.headly.Headly.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Transactional
  public User registerNewAccount(User user)  {
    if (userRepository.findByEmail(user.getEmail())!=null) {
      System.out.println("Email gibt es schon!");
    }

    String pass =  bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(pass);

    userRepository.save(user);
    return new User();
  }

}
