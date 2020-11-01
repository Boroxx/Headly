package com.headly.Headly.services;

import com.headly.Headly.models.User;
import com.headly.Headly.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class HeadlyUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    if(user== null){
        throw new UsernameNotFoundException("Kein Benutzer gefunden"+ username);
    }
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;


    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
            accountNonLocked, getAuthorities(user));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(User user) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    String role = user.getRole();
    authorities.add(new SimpleGrantedAuthority(role));

    return authorities;
  }

}
