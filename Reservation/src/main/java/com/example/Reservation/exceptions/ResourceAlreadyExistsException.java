package com.example.Hotel.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String resourceName,String fieldName, String fieldValue) {
        super(String.format("%s already exists for the given input data %s: '%s'", resourceName,fieldName, fieldValue));
    }
}
