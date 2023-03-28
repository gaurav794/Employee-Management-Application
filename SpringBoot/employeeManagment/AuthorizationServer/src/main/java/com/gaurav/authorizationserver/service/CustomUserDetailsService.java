package com.gaurav.authorizationserver.service;

import com.gaurav.resourceserver.repository.UserRepository;
import com.gaurav.resourceserver.model.UserRole;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    //Beans must not be in the same class, it will create a loop resulting in crash of the application
    //When Error occured, I have put @Bean into AuthorizationServerConfig
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param email To find the user from the database
     * @return user details
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserRole user = userRepository.findByEmail_id(email);
        if(user == null)
            throw new UsernameNotFoundException("User not found.");

        return new User(user.getEmail_id(),user.getPassword(), getAuthorities(List.of(user.getRole())));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role: roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
