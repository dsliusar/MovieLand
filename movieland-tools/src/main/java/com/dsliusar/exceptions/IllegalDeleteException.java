package com.dsliusar.exceptions;

/**
 * Created by DSliusar on 21.06.2016.
 */
public class IllegalDeleteException extends Exception {

    private String message;

    IllegalDeleteException(){}

    public IllegalDeleteException(String message) {
        super(message);
    }
}
