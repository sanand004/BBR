package com.dev.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Resource(name="userService")
	private UserDetailsService userdetailsService;
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userdetailsService).passwordEncoder(encoder());
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
	http.cors().and().csrf().disable().
	authorizeRequests()
	.antMatchers("/**").permitAll()
	.anyRequest().authenticated();
	
		
	}
	
	@Bean
	public BCryptPasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
