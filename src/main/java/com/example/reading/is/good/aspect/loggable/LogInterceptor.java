package com.example.reading.is.good.aspect.loggable;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogInterceptor {

    @Before(value = "com.example.reading.is.good.aspect.loggable.LogManager.auditLog()" + "&& target(bean) "
            + "&& @within(com.example.reading.is.good.aspect.loggable.Loggable)" + "&& @within(log)", argNames = "jp,bean,log")
    public void auditLog(JoinPoint jp, Object bean, Loggable log) {
        final Logger logger = LoggerFactory.getLogger(bean.getClass());
        if (logger.isInfoEnabled()) {
            logger.info(String.format("Log Message: %s", log.message()));
            logger.info(String.format("Bean Called: %s", bean.getClass().getName()));
            logger.info(String.format("Method Called: %s", jp.getSignature().getName()));
        }
    }

    @Around(value = "com.example.reading.is.good.aspect.loggable.LogManager.performanceLog()" + "&& target(bean) "
            + "&& @within(com.example.reading.is.good.aspect.loggable.Loggable)" + "&& @within(log)", argNames = "joinPoint,bean,log")
    public Object performanceLog(ProceedingJoinPoint joinPoint, Object bean, Loggable log) throws Throwable {
        final Logger logger = LoggerFactory.getLogger(bean.getClass());
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final Object ret = joinPoint.proceed();

        stopWatch.stop();

        final StringBuilder logMessage = new StringBuilder();
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        // append args
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logMessage.append(arg).append(",");
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }

        logMessage.append(")");
        logMessage.append(" execution time: ");
        logMessage.append(stopWatch.getTotalTimeMillis());
        logMessage.append(" ms");
        if (logger.isInfoEnabled()) {
            logger.info(logMessage.toString());
        }
        return ret;
    }
}