package org.demo.restpattern.core.exception;

public class SuperheroNotFoundException extends RuntimeException {
    public SuperheroNotFoundException(String message) {
        super(message);
    }
}
