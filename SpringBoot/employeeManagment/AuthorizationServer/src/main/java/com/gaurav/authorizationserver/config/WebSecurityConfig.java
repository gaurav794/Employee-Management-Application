package com.gaurav.authorizationserver.config;

import com.gaurav.authorizationserver.service.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig
{
    private final CORSCustomizer corsCustomizer;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public WebSecurityConfig(CORSCustomizer corsCustomizer, CustomAuthenticationProvider customAuthenticationProvider) {
        this.corsCustomizer = corsCustomizer;
        this.customAuthenticationProvider = customAuthenticationProvider;
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
//    public UserDetailsService defaultUserDetailsService()
//    {
//        //TODO: Updated with database users
//        //temp user
//        var user = User.withUsername("john").password("test").authorities("read").build();
//        //store user in repo
//        var service = new InMemoryUserDetailsManager();
//        service.createUser(user);
//        return service;
//    }

    @Autowired
    public void bindAuthenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder)
    {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
    }


//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        //TODO: Updated with BCryptPasswordEncoder();
//        return new BCryptPasswordEncoder(11);
//    }

}
