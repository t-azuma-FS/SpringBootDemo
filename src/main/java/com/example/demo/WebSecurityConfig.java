package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource datasource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/index","/msgctrl","/inputmsg").hasRole("USER")
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

      auth
          .jdbcAuthentication()
          .dataSource(datasource)
          .passwordEncoder(passwordEncoder())
          .usersByUsernameQuery(
        		  "SELECT username, password, enabled from users where username = ?")
          .authoritiesByUsernameQuery(
        		  "SELECT u.username, a.authority " +
                  "FROM user_authorities a " +
        		  "INNER JOIN users u ON u.username = a.username " +
                  "WHERE u.username = ? ");
      }

}