package com.amiroshnikov.PearStore.exception;

public class ProductNotExistsException extends IllegalArgumentException {
    public ProductNotExistsException(String message) {
        super(message);
    }
}
