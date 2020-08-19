package com.example.demo;

//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/index","/msgctrl").hasRole("USER")
				.antMatchers("/useradmin").hasRole("ADMIN")
				.antMatchers("/error").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      PasswordEncoder encoder  =
          PasswordEncoderFactories.createDelegatingPasswordEncoder();
      auth.inMemoryAuthentication()
          .withUser("user").password(encoder.encode("password")).roles("USER")
          .and()
          .withUser("admin").password(encoder.encode("adminpassword")).roles("USER","ADMIN");
     }
}