package com.dsliusar.tools.exceptions;

/**
 * Created by Red1 on 6/23/2016.
 */
public class NotFoundException extends RuntimeException {
    private String message;

    public NotFoundException(){
        super();
    }

    public NotFoundException(String message){
        super(message);
    }
}
