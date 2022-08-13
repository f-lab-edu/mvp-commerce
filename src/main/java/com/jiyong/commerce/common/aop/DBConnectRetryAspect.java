package com.jiyong.commerce.common.aop;

import com.jiyong.commerce.common.annotation.Retryable;
import com.jiyong.commerce.common.exception.RetryLimitExceededException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.jiyong.commerce.common.util.CommonUtils.sleep;

@Slf4j
@Aspect
@Order(1)
@Component
public class DBConnectRetryAspect {

    @Around("com.jiyong.commerce.common.aop.Pointcuts.repository()&&@target(annotation)")
    public Object throwAdvice(ProceedingJoinPoint point, Retryable annotation) throws Throwable {
        log.info("[throwAdvice] 현재 타겟 = {} ", point.getSignature());
        int maxCount = annotation.maxRetryValue();
        int count = 1;
        while (true) {
            try {
                return point.proceed();
            } catch (RuntimeException e) {
                Throwable cause = e.getCause();
                if (cause instanceof TimeoutException || cause instanceof IOException) {
                    log.info("예외 발생 재시도 카운트 = {}/{} , 예외명 ={}", maxCount, count, e.getMessage());
                    if (count >= maxCount) {
                        throw new RetryLimitExceededException("접속 실패");
                    }
                    sleep(500);
                } else {
                    throw e;
                }
            } catch (Exception e) {
                log.info("e.getMessage() = {} ", e.getMessage());
                throw new RuntimeException(e);
            }
            count++;
        }
    }


}
