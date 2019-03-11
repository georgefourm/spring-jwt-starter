package com.georgesdoe.sbjs.security.jwt.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        ServerHttpResponse outputMessage = new ServletServerHttpResponse(response);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        JWTAuthFailureResponse failureResponse= new JWTAuthFailureResponse(exception);

        converter.write(failureResponse, MediaType.APPLICATION_JSON,outputMessage);

        outputMessage.setStatusCode(HttpStatus.BAD_REQUEST);
        outputMessage.flush();
    }

    private class JWTAuthFailureResponse{
        private String message;

        public JWTAuthFailureResponse(AuthenticationException exception){
            this.message = exception.getMessage();
        }

        public String getMessage(){
            return message;
        }
    }
}
