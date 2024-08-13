package com.puce.delivery.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;  // Added serialVersionUID

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
