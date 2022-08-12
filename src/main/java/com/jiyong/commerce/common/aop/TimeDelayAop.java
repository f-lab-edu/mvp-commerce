package com.jiyong.commerce.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value = 2)
public class TimeDelayAop {
    @Before("execution(* com.jiyong.commerce..*Repository.*(..))")
    public void timeDelay() throws Throwable {
        Thread.sleep(100);
    }
}
