package com.georgesdoe.abe.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

@Configuration
public class JWTConfigurer {

    @Autowired
    private JWTProperties jwtProperties;

    @Bean
    public JWTVerifier getVerifier() throws UnsupportedEncodingException {

        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());

        return JWT.require(algorithm)
                .withIssuer(jwtProperties.getIssuer())
                .withAudience(jwtProperties.getAudience())
                .build();
    }
}
