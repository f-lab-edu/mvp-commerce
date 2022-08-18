package com.jiyong.commerce.common.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* *..*Repository.*(..))")
    public void repository() {
    }

    @Pointcut("execution(* *..*Memory*Repository.*(..))")

    public void MemoryRepository() {
    }

    @Pointcut("execution(* com.jiyong.commerce..*(..)) && (within(@org.springframework.web.bind.annotation.RestController *) || within(@org.springframework.stereotype.Service *) || within(@org.springframework.stereotype.Repository *))")
    public void ControllerAndServiceAndRepository() {
    }

}
