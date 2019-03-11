package com.georgesdoe.sbjs.security.jwt.filter;

import com.georgesdoe.sbjs.security.jwt.JWTAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final static String HEADER = "Authorization";
    private final static String HEADER_PREFIX = "Bearer";

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = Optional.ofNullable(request.getHeader(HEADER)).orElse("");

        String jwt = authHeader.replaceAll(HEADER_PREFIX, "").trim();

        JWTAuthenticationToken token = new JWTAuthenticationToken(jwt);
        token.setAuthenticated(false);

        SecurityContextHolder.getContext().setAuthentication(token);

        filterChain.doFilter(request,response);
    }
}
