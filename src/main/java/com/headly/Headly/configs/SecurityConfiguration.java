package com.headly.Headly.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/").permitAll()
            .antMatchers("/admin").hasAnyRole("ADMIN").and().formLogin();


    http.logout().deleteCookies("remove").invalidateHttpSession(false)
            .logoutUrl("/logout").logoutSuccessUrl("/logout");
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
            .ignoring()
            .antMatchers("/h2-console/**");
  }

}
