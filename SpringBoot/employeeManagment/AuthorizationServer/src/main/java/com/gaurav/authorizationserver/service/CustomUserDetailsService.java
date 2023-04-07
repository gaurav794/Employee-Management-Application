package com.gaurav.authorizationserver.service;

import com.gaurav.authorizationserver.model.UserRole;
import com.gaurav.authorizationserver.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService
{

    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(11);
    }

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserRole user = userRepository.findByEmail_id(email);
        if(user == null)
            throw new UsernameNotFoundException("User not found");

        return new User(user.getEmail_id(),user.getPassword(),true,true,true,true,getAuthorities(List.of(user.getRole())));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles)
    {

        List<GrantedAuthority> authorities = new ArrayList<>();
//        for(String role: roles)
//        {
            authorities.add(new SimpleGrantedAuthority("read"));
//        }

        return authorities;
    }
}
