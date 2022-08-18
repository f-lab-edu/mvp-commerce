package com.jiyong.commerce.common.aop;

import com.jiyong.commerce.common.util.logtrace.LogTrace;
import com.jiyong.commerce.common.util.logtrace.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;

import static com.jiyong.commerce.common.util.CommonUtils.getRandomNumber;
import static com.jiyong.commerce.common.util.CommonUtils.sleep;

@Slf4j
@Aspect
@RequiredArgsConstructor
@Order(3)
public class MockDelayAndThrowExceptionAspect {


    private final LogTrace logtrace;
    private final Predicate<Integer> frequency;


    @Around("com.jiyong.commerce.common.aop.Pointcuts.MemoryRepository()")
    public Object delayAdvice(ProceedingJoinPoint point) throws Throwable {
        TraceStatus status = logtrace.begin("MockDelayAndThrowExceptionAspect" + point.toShortString(), null);
        int randomDelay = getRandomNumber(20, 100);
        sleep(randomDelay);
        try {
            throwException();
            Object proceed = point.proceed();
            logtrace.end(status, null);
            return proceed;
        } catch (Exception e) {
            logtrace.exception(status, e, null);
            throw e;
        }
    }

    public void throwException() {
        int exceptionRandom = getRandomNumber(0, 99);
        int kindRandom = getRandomNumber(0, 2);
        if (frequency.test(exceptionRandom)) {
            if (kindRandom == 0) {
                throw new RuntimeException(new TimeoutException("Timeout Exception"));
            } else if (kindRandom == 1) {
                throw new RuntimeException(new IOException("IO Exception"));
            } else if (kindRandom == 2) {
                throw new RuntimeException(new SocketException("Socket Exception"));
            }
        }
    }


}
