package com.georgesdoe.abe.security;

import com.georgesdoe.abe.configuration.JWTManager;
import com.georgesdoe.abe.domain.User;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private JWTManager manager;

    JWTAuthenticationSuccessHandler(JWTManager manager){
        this.manager = manager;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        ServerHttpResponse outputMessage = new ServletServerHttpResponse(response);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        JPAUser user = (JPAUser)authentication.getPrincipal();
        String token = manager.sign(user.getId().toString());

        JWTAuthSuccessResponse successResponse = new JWTAuthSuccessResponse(token);

        converter.write(successResponse, MediaType.APPLICATION_JSON,outputMessage);

        outputMessage.flush();
    }

    private class JWTAuthSuccessResponse {

        private String token;

        JWTAuthSuccessResponse(String token){
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        void setToken(String token) {
            this.token = token;
        }

    }
}
