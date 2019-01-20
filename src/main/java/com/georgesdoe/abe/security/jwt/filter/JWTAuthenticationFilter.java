package com.georgesdoe.abe.security.jwt.filter;

import com.georgesdoe.abe.configuration.jwt.JWTManager;
import com.georgesdoe.abe.security.jwt.handler.JWTAuthenticationFailureHandler;
import com.georgesdoe.abe.security.jwt.handler.JWTAuthenticationSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager manager,JWTManager jwtManager){
        this.setAuthenticationManager(manager);

        this.setAuthenticationSuccessHandler(new JWTAuthenticationSuccessHandler(jwtManager));
        this.setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
    }
}
