package com.dsliusar.tools.exceptions;

public class UrlRequestException extends RuntimeException {

    private String message;

    public UrlRequestException() {
        super();
    }
    public UrlRequestException(String message) {
        super(message);
    }
}
