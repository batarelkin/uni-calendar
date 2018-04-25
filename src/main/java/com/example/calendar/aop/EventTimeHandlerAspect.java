package com.example.calendar.aop;

import com.example.calendar.models.Event;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.*;

@Aspect
@Component
public class EventTimeHandlerAspect {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "&& execution(public com.example.calendar.models.Event *(..))")
    public Object hideEventDescription(ProceedingJoinPoint joinPoint) throws Throwable {
        Event result = (Event)joinPoint.proceed();
        result.setStart(result.getStart().plusHours(3));
        result.setEnd(result.getEnd().plusHours(3));
        return result;
    }

}
