package ru.gil.tacocloud.exception_handler;

public class ResourceException extends RuntimeException{
    public ResourceException() {
        super();
    }

    public ResourceException(String message) {
        super(message);
    }
}
