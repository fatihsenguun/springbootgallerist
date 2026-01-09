package com.fatihsengun.config;

import com.fatihsengun.jwt.AuthTokenEntryPoint;
import com.fatihsengun.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor 
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AuthTokenEntryPoint authTokenEntryPoint;

    // İzin verilecek URL'ler
    private static final String[] WHITE_LIST_URLS = {
            "/authenticate",
            "/register",
            "/refreshToken",
            "/swagger-ui/**",     
            "/v3/api-docs/**",
            "swagger-ui.html",
            "/api/ai/**"          
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> request
                .requestMatchers(WHITE_LIST_URLS).permitAll() 
                .anyRequest().authenticated()                
            )

           
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(authTokenEntryPoint)
            )

          
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

         
            .authenticationProvider(authenticationProvider)

          
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}