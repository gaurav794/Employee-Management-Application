package com.gaurav.authorizationserver.config;


import com.gaurav.authorizationserver.service.CustomAuthenticationProvider;
import com.gaurav.authorizationserver.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    private final CORSCustomizer corsCustomizer;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;
    private final CustomAuthenticationProvider authenticationProvider;

    public WebSecurityConfig(CORSCustomizer corsCustomizer, PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService, CustomAuthenticationProvider authenticationProvider) {
        this.corsCustomizer = corsCustomizer;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        //Allow requests from different clients meaning from other than auth server
        corsCustomizer.corsCustomizer(http);

        return http
//                .formLogin().loginPage("http://localhost:4200/login")
                .formLogin()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .build();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider()
//    {
//        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//            dao.setUserDetailsService(userDetailsService);
//            dao.setPasswordEncoder(passwordEncoder);
//            return dao;
//    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        return authenticationManagerBuilder.build();
    }

}
