package org.demo.restpattern.core.exception;

public class InvalidSuperheroDataException extends RuntimeException {
    public InvalidSuperheroDataException(String message) {
        super(message);
    }
}
