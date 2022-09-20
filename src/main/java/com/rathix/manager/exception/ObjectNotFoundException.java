package com.rathix.manager.exception;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException() {
        super("The queried object could not be found.");
    }

    public ObjectNotFoundException(String s) {
        super("The queried object with id " + s + " could not be found.");
    }
}
