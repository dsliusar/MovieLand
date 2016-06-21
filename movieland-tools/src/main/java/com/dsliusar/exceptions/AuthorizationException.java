package com.dsliusar.exceptions;

/**
 * Created by DSliusar on 21.06.2016.
 */
public class AuthorizationException extends Exception{
    private String message;

    public AuthorizationException(){}

    public AuthorizationException(String message) {
        super(message);
    }
}
