package com.georgesdoe.abe.exception.handling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e,WebRequest request){
        Map<String,String> messages = new HashMap<>();
        for (ConstraintViolation violation : e.getConstraintViolations()){
            messages.put(violation.getPropertyPath().toString(),violation.getMessage());
        }
        ValidationException exception = new ValidationException("Validation Failed");

        return handleExceptionInternal(exception,messages,new HttpHeaders(),HttpStatus.UNPROCESSABLE_ENTITY,request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        APIExceptionResponse response = new APIExceptionResponse(ex);
        response.setDetails(body);

        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(response);
    }
}
