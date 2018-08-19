package com.georgesdoe.abe.security;

import com.georgesdoe.abe.configuration.JWTManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager manager,JWTManager jwtManager){
        this.setAuthenticationManager(manager);

        this.setAuthenticationSuccessHandler(new JWTAuthenticationSuccessHandler(jwtManager));
        this.setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
    }
}
