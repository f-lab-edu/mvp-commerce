package com.jiyong.commerce.common.aop;

import com.jiyong.commerce.common.util.logtrace.LogTrace;
import com.jiyong.commerce.common.util.logtrace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@Order(1)
public class LoggingAspect {
    private final LogTrace logtrace;

    public LoggingAspect(LogTrace logtrace) {
        this.logtrace = logtrace;
    }


    @Around("com.jiyong.commerce.common.aop.Pointcuts.ControllerAndServiceAndRepository())")
    public Object loggerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodSignature = joinPoint.getSignature().toShortString();


        TraceStatus status = logtrace.begin(methodSignature, joinPoint.getArgs());
        try {
            Object proceed = joinPoint.proceed();
            logtrace.end(status, String.valueOf(proceed));
            return proceed;
        } catch (Exception e) {
            logtrace.exception(status, e, null);
            throw e;
        }
    }
}
