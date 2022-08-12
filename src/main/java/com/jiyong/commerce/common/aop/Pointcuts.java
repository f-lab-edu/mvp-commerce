package com.jiyong.commerce.common.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* *..*Repository.*(..))")
    public void repository() {
    }

    @Pointcut("execution(* *..*Memory*Repository.*(..))")
    public void MemoryRepository() {
    }


}
