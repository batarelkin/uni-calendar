package com.example.calendar.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ProfilingAspect {

    @Around("@annotation(com.example.calendar.aop.Profiling)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long end = System.nanoTime();
        Signature signature = joinPoint.getSignature();
        String method = String.format("%s#%s", signature.getDeclaringTypeName(), signature.getName());
        log.info("{} took {} milliseconds", method, TimeUnit.NANOSECONDS.toMillis(end - start));
        return result;
    }

}
