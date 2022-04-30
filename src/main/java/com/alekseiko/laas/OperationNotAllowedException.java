package com.alekseiko.laas;

public class OperationNotAllowedException extends Exception {
    public OperationNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}
