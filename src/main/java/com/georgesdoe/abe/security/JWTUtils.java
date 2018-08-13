package com.georgesdoe.abe.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class JWTUtils {

    public static String encode(Map<String,Claim> claims) throws UnsupportedEncodingException{
        Algorithm algorithm = Algorithm.HMAC256("${app.jwt.secret}");
        String token = JWT.create()
                .withIssuer("abe")
                .withIssuedAt(Date.from(Instant.now()))
                .withSubject("sub")
                .sign(algorithm);
        return token;
    }

    public static Map<String, Claim> decode(String token)
            throws UnsupportedEncodingException,JWTVerificationException{

        Algorithm algorithm = Algorithm.HMAC256("${app.jwt.secret}");

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance

         DecodedJWT jwt = verifier.verify(token);
         return jwt.getClaims();
    }
}
