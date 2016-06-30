package com.dsliusar.tools.exceptions;

/**
 * Created by DSliusar on 24.06.2016.
 */
public class RequestFormatException extends RuntimeException {
    private String message;

    public RequestFormatException(){
        super();
    }

    public RequestFormatException(String message){
        super(message);
    }

}
