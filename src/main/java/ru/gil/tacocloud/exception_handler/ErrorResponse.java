package ru.gil.tacocloud.exception_handler;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String Message;
    public ErrorResponse(String message) {
        Message = message;
    }
}
