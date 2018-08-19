package com.georgesdoe.abe.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTManager {

    private Algorithm algorithm;

    private JWTProperties jwtProperties;

    @Autowired
    public JWTManager(@NotNull JWTProperties properties) throws UnsupportedEncodingException {
        jwtProperties = properties;
        algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
    }

    public String decode(String jwt){
        return JWT.require(algorithm)
                .withIssuer(jwtProperties.getIssuer())
                .withAudience(jwtProperties.getAudience())
                .build()
                .verify(jwt)
                .getSubject();
    }

    public String sign(String subject) {
        Date expiration = Date.from(Instant.now()
                .plus(jwtProperties.getExpiration(),ChronoUnit.MINUTES)
        );

        return JWT.create()
                .withIssuer(jwtProperties.getIssuer())
                .withAudience(jwtProperties.getAudience())
                .withExpiresAt(expiration)
                .withSubject(subject)
                .sign(algorithm);
    }
}
