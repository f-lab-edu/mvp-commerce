package com.jiyong.commerce.common.annotation;


import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.SocketException;
import java.util.concurrent.TimeoutException;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Retryable {
    int maxRetryValue() default 5;

    Class<? extends Exception>[] exception() default {TimeoutException.class, IOException.class, SocketException.class};


}
