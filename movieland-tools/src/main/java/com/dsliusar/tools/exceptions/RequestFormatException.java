package com.dsliusar.tools.exceptions;

/**
 * Created by DSliusar on 24.06.2016.
 */
public class RequestFormatException extends Exception {
    private String message;

    public RequestFormatException(){}

    public RequestFormatException(String message){
        super(message);
    }

}
