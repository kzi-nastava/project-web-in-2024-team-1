package com.webshop.exception;

public class GmailAlreadyExistsException extends  RuntimeException {
    public GmailAlreadyExistsException(String message) {
        super(message);
    }
}
