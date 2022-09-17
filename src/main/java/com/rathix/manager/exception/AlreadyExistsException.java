package com.rathix.manager.exception;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String s) {
        super(s);
    }
}
