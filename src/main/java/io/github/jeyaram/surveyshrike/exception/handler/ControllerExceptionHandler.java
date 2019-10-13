package io.github.jeyaram.surveyshrike.exception.handler;

import io.github.jeyaram.surveyshrike.exception.ResourceNotFoundException;
import io.github.jeyaram.surveyshrike.exception.UnsatisfiedConditionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
@Log4j2
public class ControllerExceptionHandler {

    @ExceptionHandler(UnsatisfiedConditionException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleUnsatisfiedConditionException(HttpServletRequest request, Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Resource not found")
    public void handleResourceNotFoundException(HttpServletRequest request, Exception ex) {
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,reason = "Error while pushing file to s3")
    public void handleIOExceptionInPushingToS3(HttpServletRequest request, Exception ex) {
    }


}
