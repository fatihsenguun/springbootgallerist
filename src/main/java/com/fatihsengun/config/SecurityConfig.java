package com.fatihsengun.config;

import com.fatihsengun.jwt.AuthTokenEntryPoint;
import com.fatihsengun.jwt.JwtAuthenticationFilter;
import com.fatihsengun.starter.GalleristApplicationStarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private AuthTokenEntryPoint authTokenEntryPoint;
	

	private static final String AUTHENTICATE = "/authenticate";
	private static final String REGISTER = "/register";
	private static final String REFRESH_TOKEN = "/refreshToken";

    SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http.csrf(csrf-> csrf.disable())
		.authorizeHttpRequests(request->
		request.requestMatchers(AUTHENTICATE,REGISTER,REFRESH_TOKEN)
		.permitAll()
		.anyRequest()
		.authenticated())
		.exceptionHandling(exception->exception.authenticationEntryPoint(authTokenEntryPoint))
		.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

}
