package com.headly.Headly.services;


import com.headly.Headly.ErrorHandling.TemplateError;
import com.headly.Headly.models.User;
import com.headly.Headly.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

@Service
@Scope
public class RegistrationService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  private TemplateError templateError = new TemplateError(null, false);

  @Transactional
  public void registerNewAccount(User user)  {
    if (userRepository.findByEmail(user.getEmail())!=null) {
      System.out.println("Email gibt es schon!");
      templateError.setError("Email schon vorhanden");
      templateError.setExists(true);
    }else{
      String pass =  bCryptPasswordEncoder.encode(user.getPassword());
      user.setPassword(pass);
      String role_temp = user.getRole();
      String role = "ROLE_" + role_temp.toUpperCase();
      user.setRole(role);
      userRepository.save(user);

    }


  }

    int findUserIdByUsername(String username) {
    return userRepository.findByEmail(username).getId();

  }

  public TemplateError getTemplateError(){
    return this.templateError;
  }

  public void resetTemplateError(){
    this.templateError.setError("");
    this.templateError.setExists(false);
  }

  public User findUserById(String username){
    return userRepository.findByEmail(username);
  }
}
