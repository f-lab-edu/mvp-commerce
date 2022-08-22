package com.jiyong.commerce.itemCategory.exception;

public class NoSuchParentCategoryException extends RuntimeException {
    public NoSuchParentCategoryException(String message) {
        super(message);
    }
}
