package com.jiyong.commerce.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.TimeoutException;

@Aspect
@Component
@Order(value = 1)
public class ExceptionAop {
    @Before("execution(* com.jiyong.commerce..*Repository.*(..))")
    public void throwException() throws Throwable {
        if ((int) (Math.random() * 100) < 1) {
            int randomNumber = (int) (Math.random() * 3) + 1;
            try {
                selectException(randomNumber);
            } catch (Exception e) {
                selectException(randomNumber);
            }
        }
    }

    public void selectException(int randomNumber) throws IOException, TimeoutException {
        if (randomNumber == 1) {
            throw new IOException("IOException 발생");
        } else if (randomNumber == 2) {
            throw new SocketException("SocketException 발생");
        } else {
            throw new TimeoutException("TimeoutException 발생");

        }
    }
}
