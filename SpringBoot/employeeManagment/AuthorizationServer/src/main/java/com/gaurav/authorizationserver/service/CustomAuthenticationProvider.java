package com.gaurav.authorizationserver.service;

import com.gaurav.authorizationserver.model.UserRole;
import com.gaurav.authorizationserver.repository.UserRepository;
import com.gaurav.authorizationserver.service.util.LoginUserDetails;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public CustomAuthenticationProvider(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        //email
        String username = authentication.getName();
        //Verification of password against database info
        UserRole user = userRepository.findByEmail_id(username);
        if(user == null)
            throw new BadCredentialsException("User not found");


        return passwordCheck(authentication, new LoginUserDetails(user));
    }


    private Authentication passwordCheck(Authentication authentication, LoginUserDetails user)
    {
        String presentedPassword = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(presentedPassword,user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        //Manually setting the context
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        return UsernamePasswordAuthenticationToken
                .authenticated(user.getUsername(),user.getPassword(),new ArrayList<>());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
