package com.georgesdoe.abe.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.georgesdoe.abe.domain.User;
import com.georgesdoe.abe.exception.ResourceNotFoundException;
import com.georgesdoe.abe.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

class JWTUserService {

    private JWTVerifier verifier;
    private Algorithm algorithm;
    private UserRepository repository;

    JWTUserService(UserRepository repository) throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256("${app.jwt.secret}");

        verifier = JWT.require(algorithm)
                .withIssuer("${app.jwt.issuer}")
                .build();

        this.repository = repository;
    }

    String tokenize(User user) {
        return JWT.create()
                .withIssuer("${app.jwt.issuer}")
                .withIssuedAt(Date.from(Instant.now()))
                .withSubject(user.getId().toString())
                .sign(algorithm);
    }

    Optional<User> retrieve(String token){
        try{
            String subject = verifier.verify(token).getSubject();

            Long id = Long.parseLong(subject);

            return repository.findById(id);
        } catch (NumberFormatException | JWTVerificationException e){
            return Optional.empty();
        }
    }
}
