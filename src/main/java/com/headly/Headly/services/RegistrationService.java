package com.headly.Headly.services;


import com.headly.Headly.ErrorHandling.TemplateLogger;
import com.headly.Headly.models.User;
import com.headly.Headly.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope
public class RegistrationService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  private TemplateLogger templateLogger = new TemplateLogger(null, false);

  @Transactional
  public void registerNewAccount(User user)  {
    if (userRepository.findByEmail(user.getEmail())!=null) {
      System.out.println("Email gibt es schon!");
      templateLogger.setError("Email schon vorhanden");
      templateLogger.setExists(true);
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

  public TemplateLogger getTemplateLogger(){
    return this.templateLogger;
  }

  public void resetTemplateError(){
    this.templateLogger.setError("");
    this.templateLogger.setExists(false);
  }

  public User findUserById(String username){
    return userRepository.findByEmail(username);
  }
}
