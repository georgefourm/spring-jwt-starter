package com.georgesdoe.abe.exception;

public class ResourceNotFoundException extends APIException {

    private String resource;

    public ResourceNotFoundException(Class type){
        this.resource = type.getSimpleName();
    }

    @Override
    public String getMessage() {
        return resource + " not found";
    }
}
