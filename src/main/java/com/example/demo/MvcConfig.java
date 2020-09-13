package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/index").setViewName("index");
	        registry.addViewController("/").setViewName("index");
	        //registry.addViewController("/useradmin").setViewName("useradmin");
	        //registry.addViewController("/msgctrl").setViewName("msgctrl");
	        registry.addViewController("/login").setViewName("login");
	        registry.addViewController("/error").setViewName("error");
	        //registry.addViewController("/inputmsg").setViewName("inputmsg");
	}
}
