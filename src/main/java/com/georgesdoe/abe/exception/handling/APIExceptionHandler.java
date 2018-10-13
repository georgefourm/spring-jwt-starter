package com.georgesdoe.abe.exception.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<APIExceptionResponse> handle(Exception e){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIExceptionResponse(e));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<APIExceptionResponse> handle(ConstraintViolationException e){
        APIExceptionResponse response = new APIExceptionResponse();
        response.setMessage("Validation Failed");
        response.setError("ValidationException");

        Map<String,String> messages = new HashMap<>();
        for (ConstraintViolation violation : e.getConstraintViolations()){
            messages.put(violation.getPropertyPath().toString(),violation.getMessage());
        }
        response.setDetails(messages);

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }
}
