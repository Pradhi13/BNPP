package com.bnpp.kata.bookdiscountsale.exception;

public class InvalidBookException extends IllegalArgumentException {
    public InvalidBookException(String message) {
        super(message);
    }
}
