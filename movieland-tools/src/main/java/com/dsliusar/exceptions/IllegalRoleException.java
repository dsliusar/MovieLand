package com.dsliusar.exceptions;

public class IllegalRoleException extends Exception {

    private String message;

    public IllegalRoleException(){

    }

    public IllegalRoleException(String message){
        super(message);
    }


}
