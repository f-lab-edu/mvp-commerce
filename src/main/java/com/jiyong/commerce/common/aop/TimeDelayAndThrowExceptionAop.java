package com.jiyong.commerce.common.aop;

import com.jiyong.commerce.common.util.CommonUtils;
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

@Slf4j
@Aspect
@RequiredArgsConstructor
@Order(value = 2)
public class TimeDelayAndThrowExceptionAop {

    private final Predicate<Integer> frequency;

    @Around("com.jiyong.commerce.common.aop.Pointcuts.memoryRepository()")
    public Object timeDelayAndThrowException(ProceedingJoinPoint point) throws Throwable {
        CommonUtils.sleep(100);
        try {
            throwException();
            return point.proceed();
        } catch (Exception e) {
            throw e;
        }
    }

    public void throwException() {
        int exceptionNumber = getRandomNumber(0, 99);
        int randomNumber = CommonUtils.getRandomNumber(1, 3);
        if (frequency.test(exceptionNumber)) {
            switch (randomNumber) {
                case 1:
                    throw new RuntimeException(new IOException("mock : IO Exception"));
                case 2:
                    throw new RuntimeException(new SocketException("mock : Socket Exception"));
                case 3:
                    throw new RuntimeException(new TimeoutException("mock : Timeout Exception"));
            }
        }
    }
}
