package com.georgesdoe.abe.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.georgesdoe.abe.configuration.jwt.JWTManager;
import com.georgesdoe.abe.domain.User;
import com.georgesdoe.abe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private JWTManager jwtManager;

    private UserRepository repository;

    @Autowired
    JWTAuthenticationProvider(JWTManager manager, UserRepository repository) {
        this.jwtManager = manager;
        this.repository = repository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JWTAuthenticationToken token = (JWTAuthenticationToken) authentication;

        try {
            String jwt = (String) token.getCredentials();
            String subject = jwtManager.decode(jwt);

            Long id = Long.parseLong(subject);

            User user = repository.findById(id)
                    .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));

            return new UsernamePasswordAuthenticationToken(user, jwt, null);
        } catch (JWTVerificationException | NumberFormatException e) {
            throw new BadCredentialsException(e.getMessage(), e);
        }

    }

    @Override
    public boolean supports(Class<?> authClass) {
        return authClass.isAssignableFrom(JWTAuthenticationToken.class);
    }
}
