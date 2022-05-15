package com.example.flightreservation.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAdvice {

    @Pointcut("execution(* com.example.flightreservation.service.impl.*Impl.create*(..))")
    private void createPointCut(){}

    @Around("createPointCut()")
    public Object createLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().toString();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] args = proceedingJoinPoint.getArgs();

        log.info("method invoked {} : {}. Arguments: {}", className, methodName, mapper.writeValueAsString(args));

        Object object = proceedingJoinPoint.proceed();

        log.info("{} : {}(). Response : ", className, mapper.writeValueAsString(object));
        return object;
    }
}
