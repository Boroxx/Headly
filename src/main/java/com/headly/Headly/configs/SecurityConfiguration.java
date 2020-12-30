package com.headly.Headly.configs;


import com.headly.Headly.services.HeadlyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  HeadlyUserDetailsService headlyUserDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(headlyUserDetailsService).passwordEncoder(passwordEncoder());

  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable();
    http.anonymous().disable();
    http.authorizeRequests().antMatchers("/").permitAll()
            .antMatchers("/admin/**").hasAnyRole("UNTERNEHMEN","BEWERBER")
            .antMatchers("/admin/").hasAnyRole("UNTERNEHMEN","BEWERBER")
            .antMatchers("/admin").hasAnyRole("UNTERNEHMEN","BEWERBER")


            .and().formLogin().loginPage("/login").failureUrl("/loginerror").loginProcessingUrl("/login").defaultSuccessUrl("/loginredirect").and().requiresChannel()
            .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
            .requiresSecure();
    ;;


    http.logout().deleteCookies("remove").invalidateHttpSession(false)
            .logoutUrl("/logout").logoutSuccessUrl("/");
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
            .ignoring()
            .antMatchers("/h2-console/**");
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
