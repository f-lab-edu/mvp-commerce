package com.jiyong.commerce.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;
import java.sql.Time;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;

@Slf4j
@Aspect
public class AspectConfig {
    private  int maxPercent;
    private  int minPercent;
    private  Predicate<Integer> frequency;            ;

    public AspectConfig(int maxPercent, int minPercent, Predicate<Integer> frequency) {
        this.maxPercent = maxPercent;
        this.minPercent = minPercent;
        this.frequency = frequency;
    }

    @Pointcut("execution(* *..*Repository.*(..))")
    public void repository() {
    }


    @After("repository()")
    public void delayAdvice(JoinPoint point) {
        log.info("[delayAdvice] 현재 타겟 = {} ", point.getSignature());
        int randomDelay = getRandomNumber(20, 100);
        log.info("randomDelay = {}ms ", randomDelay);
        sleep(randomDelay);
    }


    /**
     * ToDo
     * 성공률 99% (1% = IOException, SocketException, TimeoutException - RuntimeException의 하위, Retryable exponentially 인지 아닌지) AuthorizationException
     * (Math.random() 0 ~ 1) * 100 < 1
     * */



    @Around("repository()")
    public Object throwAdvice(ProceedingJoinPoint point) throws Throwable {
        log.info("[throwAdvice] 현재 타겟 = {} ", point.getSignature());
        int count = 0;
        while (true) {
            try {
                throwException();
                return point.proceed();
            } catch (TimeoutException | IOException e) {
                log.info("예외 발생 재시도 카운트 ={} , 예외명 ={}", count, e.getMessage());
                if (count >= 4) {
                    throw new RuntimeException(e);
                }
                sleep(500);
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
            count++;
        }
    }


    private void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private void throwException() throws TimeoutException, IOException {
        int exceptionRandom = getRandomNumber(minPercent, maxPercent);
        int kindRandom = getRandomNumber(0, 2);
        if (frequency.test(exceptionRandom)) {
            if (kindRandom == 0) {
                throw new TimeoutException("TimeOut");
            } else if (kindRandom == 1) {
                throw new IOException("IO Exception");
            } else {
                throw new SocketException("SocketException");
            }
        }
    }




}
