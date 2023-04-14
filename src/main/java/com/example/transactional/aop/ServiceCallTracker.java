package com.example.transactional.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceCallTracker {

    @Pointcut("within(com..service.*) || within(com..repo.*)")
    public void logMethodPointCut() {

    }

    @Around("logMethodPointCut()")
    public Object logAroundPointCut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println(" method start : " + proceedingJoinPoint.getSignature().getName());

        Object result = proceedingJoinPoint.proceed();

        System.out.println(result);

        System.out.println(" method completed : " + proceedingJoinPoint.getSignature().getName());

        return result;
    }
}
