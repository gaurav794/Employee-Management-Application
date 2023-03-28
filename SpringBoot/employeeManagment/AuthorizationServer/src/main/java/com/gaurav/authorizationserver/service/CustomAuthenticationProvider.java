package com.gaurav.authorizationserver.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private CustomUserDetailsService customUserDetailsService;

    private PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    //Authenticate the user when logs in
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails user = customUserDetailsService.loadUserByUsername(username);
        //TODO: Check the result of user

        return checkPassword(user,password);
    }

    //Compare the passwords
    private Authentication checkPassword(UserDetails user, String password) {
        if(passwordEncoder.matches(user.getPassword(),password))
        {
            return new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(), user.getAuthorities());
        }
        else
            throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
