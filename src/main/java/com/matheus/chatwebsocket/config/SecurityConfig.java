package com.matheus.chatwebsocket.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.matheus.chatwebsocket.service.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;


    @Bean 
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .headers(header ->header.frameOptions().sameOrigin())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/chat/message").permitAll()
                .requestMatchers(HttpMethod.POST, "/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/chat/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/chat/message").permitAll()
                .requestMatchers(HttpMethod.GET, "/chat/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/**").permitAll() 
                .requestMatchers(HttpMethod.GET, "/conect").permitAll()
                    .anyRequest().permitAll()
                )
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean 
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}