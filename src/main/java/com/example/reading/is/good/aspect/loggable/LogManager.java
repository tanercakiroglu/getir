package com.example.reading.is.good.aspect.loggable;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogManager {

    @Pointcut("execution( * *(..))")
    public void auditLog() {
    }

    @Pointcut("execution( * *(..))")
    public void performanceLog() {
    }

}
