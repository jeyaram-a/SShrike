package io.github.jeyaram.surveyshrike.exception.handler;

import io.github.jeyaram.surveyshrike.exception.ResourceNotFoundException;
import io.github.jeyaram.surveyshrike.exception.UnsatisfiedConditionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Log4j2
public class ControllerExceptionHandler {

    @ExceptionHandler(UnsatisfiedConditionException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleUnsatisfiedConditionException(HttpServletRequest request, Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Resource not found")
    public void handleResourceNotFoundException(HttpServletRequest request, Exception ex) {
    }


}
