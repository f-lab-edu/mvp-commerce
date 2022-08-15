package com.jiyong.commerce.common.aop;

import com.jiyong.commerce.common.annotation.Retry;
import com.jiyong.commerce.common.exception.RetryLimitExceededException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.TimeoutException;

@Slf4j
@Aspect
@Component
@Order(value = 1)
public class RetryAop {
    @Around("com.jiyong.commerce.common.aop.Pointcuts.repository()&&@target(retry)")
    public Object retryAdvice(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable{
        int maxRetry = retry.maxRetryValue();

        for (int i=0; i<=maxRetry; i++) {
            if (i >= 1) {
                log.info("{} 번째 재시도", i);
            }
            try {
                return joinPoint.proceed();
            } catch (RuntimeException re) {
                Throwable cause = re.getCause();
                if (cause instanceof IOException || cause instanceof SocketException || cause instanceof TimeoutException) {
                    log.info("예외명 = {}", re.getMessage());
                } else {
                    throw re;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        throw new RetryLimitExceededException("재시도 실패");
    }
}
