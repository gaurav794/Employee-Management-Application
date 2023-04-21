package com.gaurav.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{
    private final CORSCustomizerResource corsCustomizerResource;

    public SecurityConfig(CORSCustomizerResource corsCustomizerResource)
    {
        this.corsCustomizerResource = corsCustomizerResource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        this.corsCustomizerResource.corsCustomizer(http);

        return http.oauth2ResourceServer().jwt(jwk -> jwk.jwkSetUri("http://localhost:8080/oauth2/jwks")).and()
                .authorizeRequests()
                .anyRequest()
                .authenticated().and().build();
    }
}

