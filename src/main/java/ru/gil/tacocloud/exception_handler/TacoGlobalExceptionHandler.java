package ru.gil.tacocloud.exception_handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class TacoGlobalExceptionHandler {

    @ExceptionHandler(ResourceException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), NOT_FOUND);
    }

}
