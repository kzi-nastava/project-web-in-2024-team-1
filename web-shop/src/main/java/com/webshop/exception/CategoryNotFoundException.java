package com.webshop.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String msg) {
        super(msg);
    }
}
