package com.georgesdoe.abe.exception;

public class ResourceNotFoundException extends APIException {

    private Class resource;

    public ResourceNotFoundException(Class type){
        this.resource = type;
    }

    @Override
    public String getMessage() {
        return resource + " not found";
    }
}
