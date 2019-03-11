package com.georgesdoe.sbjs.exception.handling;

public class APIExceptionResponse {

    private String message;

    private String error;

    private Object details;

    public APIExceptionResponse(Exception e){
        message = e.getMessage();
        error = e.getClass().getSimpleName();
    }

    public APIExceptionResponse(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}
