package com.gaurav.authorizationserver.config;

import com.gaurav.authorizationserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class DefaultSecurityConfig {

    //Bind it with authenticationManager
    private CustomAuthenticationProvider customAuthenticationProvider;

    public DefaultSecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    SecurityFilterChain deafaultSecurityFilterChain(HttpSecurity http) throws Exception
    {
       http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated()).formLogin(Customizer.withDefaults());

       return http.build();
    }

    @Autowired
    public void bindCustomAuthenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder)
    {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
    }

}
