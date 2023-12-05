package com.example.catalog.services.exceptions;

public class InvalidUuidException extends IllegalArgumentException {
    public InvalidUuidException(String msg) {
        super(msg);
    }
}
