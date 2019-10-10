package io.github.jeyaram.surveyshrike.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceId) {
        super("Reource "+ resourceId+" not found");
    }
}
