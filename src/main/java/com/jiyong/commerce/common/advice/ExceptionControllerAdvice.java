package com.jiyong.commerce.common.advice;

import com.jiyong.commerce.common.exception.RetryLimitExceededException;
import com.jiyong.commerce.itemCategory.exception.NoSuchParentCategoryException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchParentCategoryException.class)
    public ErrorCode noSuchParentCategoryException(NoSuchParentCategoryException e) {
        return new ErrorCode(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(RetryLimitExceededException.class)
    public ErrorCode retryLimitExceededException(RetryLimitExceededException e) {
        return new ErrorCode(e.getLocalizedMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }


    @AllArgsConstructor
    @Getter
    static class ErrorCode {
        private String errorMessage;
        private HttpStatus errorCode;
    }
}