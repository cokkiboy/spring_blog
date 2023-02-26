package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetailService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =true)
public class SecurityConfig {
	
	private PrincipalDetailService principalDetailService;
	@Bean
    public BCryptPasswordEncoder endePWD() {
    	return new BCryptPasswordEncoder();
    }
	public void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(principalDetailService).passwordEncoder(endePWD());
		
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.csrf().disable()
		  .authorizeRequests()
		    .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
			.permitAll()
			.anyRequest()
			.authenticated()
		  .and()
		    .formLogin()
		    .loginPage("/auth/loginForm")
		    .loginProcessingUrl("/auth/loginProc")
		    .defaultSuccessUrl("/");
		
		
		return http.build();
	
}
}