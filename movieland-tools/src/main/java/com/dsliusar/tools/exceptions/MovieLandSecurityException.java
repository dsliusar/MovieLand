package com.dsliusar.tools.exceptions;


public class MovieLandSecurityException extends RuntimeException {

    private String message;
    private String type;

    public MovieLandSecurityException(){
        super();
    }

    public MovieLandSecurityException(String message){
        super(message);
    }

    public MovieLandSecurityException(String message, String type) {
        super(message);
        this.type = type;
    }

    @Override
    public String toString(){
         return super.toString();
    }

}
