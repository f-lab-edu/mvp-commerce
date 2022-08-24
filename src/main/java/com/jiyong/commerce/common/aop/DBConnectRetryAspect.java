package com.jiyong.commerce.common.aop;

import com.jiyong.commerce.common.annotation.Retryable;
import com.jiyong.commerce.common.exception.RetryLimitExceededException;
import com.jiyong.commerce.common.util.logtrace.LogTrace;
import com.jiyong.commerce.common.util.logtrace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.jiyong.commerce.common.util.CommonUtils.sleep;


@Slf4j
@Aspect
@Order(2)
@Component
public class DBConnectRetryAspect {
    private final LogTrace logtrace;

    public DBConnectRetryAspect(LogTrace logtrace) {
        this.logtrace = logtrace;
    }

    @Around("com.jiyong.commerce.common.aop.Pointcuts.repository()&&@target(annotation)")
    public Object throwAdvice(ProceedingJoinPoint joinPoint, Retryable annotation) throws Throwable {
        TraceStatus status = logtrace.begin("DBConnectRetryAspect 현재 타겟 = " + joinPoint.toShortString(), null);
        int maxCount = annotation.maxRetryValue();
        Class<? extends Exception>[] exception = annotation.exception();
        int count = 1;
        while (true) {
            try {
                Object proceed = joinPoint.proceed();
                logtrace.end(status, null);
                return proceed;
            } catch (RuntimeException e) {
                boolean exceptionCheck = Arrays.stream(exception).anyMatch(i -> ExceptionUtils.indexOfType(e, i) != -1);
                if (exceptionCheck) {
                    logtrace.keep(status, String.format("예외 발생 재시도 카운트 = %d/%d , 예외명 = %s", maxCount, count, e.getMessage()));
                    if (count >= maxCount) {
                        throw new RetryLimitExceededException("접속 실패");
                    }
                    sleep(500);
                } else {
                    logtrace.exception(status, e, null);
                    throw e;
                }
            } catch (Exception e) {
                logtrace.exception(status, e, null);
                throw e;
            }
            count++;
        }
    }
}
