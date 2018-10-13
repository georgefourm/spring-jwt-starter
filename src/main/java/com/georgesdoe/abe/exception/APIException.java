package com.georgesdoe.abe.exception;

public class APIException extends RuntimeException{

    public APIException(Exception cause){
        super(cause);
    }

    public APIException() {
        super();
    }
}
