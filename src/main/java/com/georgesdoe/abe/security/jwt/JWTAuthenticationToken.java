package com.georgesdoe.abe.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

    private String token;

    public JWTAuthenticationToken(String token) {
        super(new ArrayList<>());
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
