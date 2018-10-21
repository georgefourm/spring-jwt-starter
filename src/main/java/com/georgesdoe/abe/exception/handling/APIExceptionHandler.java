package com.georgesdoe.abe.exception.handling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String,String> messages = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()){
            messages.put(error.getField(),error.getDefaultMessage());
        }

        APIExceptionResponse response = new APIExceptionResponse();
        response.setError("ValidationError");
        response.setMessage("Validation Failed");
        response.setDetails(messages);

        return handleExceptionInternal(ex, response, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        APIExceptionResponse response = new APIExceptionResponse(ex);
        response.setDetails(body);

        if (body instanceof APIExceptionResponse){
            response = (APIExceptionResponse) body;
        }

        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(response);
    }
}
