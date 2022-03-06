package com.amiroshnikov.PearStore.exception;

public class CustomException extends IllegalArgumentException{

    public CustomException(String message) {
        super(message);
    }
}
