package com.dsliusar.tools.exceptions;

/**
 * Created by DSliusar on 24.06.2016.
 */
public class RequestException extends RuntimeException {
    private String message;

    public RequestException(){
        super();
    }

    public RequestException(String message){
        super(message);
    }

}
