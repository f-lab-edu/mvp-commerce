package com.jiyong.commerce.common.exception;

public class RetryLimitExceededException extends RuntimeException{
    public RetryLimitExceededException(String message) {
        super(message);
    }
}
