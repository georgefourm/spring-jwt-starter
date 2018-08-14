package com.georgesdoe.abe.security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private JWTVerifier verifier;

    @Autowired
    JWTAuthenticationProvider(JWTVerifier verifier){
        this.verifier = verifier;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JWTAuthenticationToken token = (JWTAuthenticationToken) authentication;

        try{
            String subject = verifier.verify(token.getToken()).getSubject();

            return new UsernamePasswordAuthenticationToken(subject,null);
        } catch (JWTVerificationException e){
            throw new BadCredentialsException("Invalid authentication header");
        }

    }

    @Override
    public boolean supports(Class<?> authClass) {
        return authClass.isAssignableFrom(JWTAuthenticationToken.class);
    }
}
