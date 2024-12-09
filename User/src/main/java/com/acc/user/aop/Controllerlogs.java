package com.acc.user.aop;

import com.acc.user.util.Convertion;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Controllerlogs {
    @Pointcut("within(com.acc.user.controller.*)")
    public void controllerPointcut(){

    }
    @Around("controllerPointcut()")
    public Object controllerLogsAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("UserController :: calling {} method and arguments were {}",joinPoint.getSignature().getName(),joinPoint.getArgs());
        Object obj=joinPoint.proceed();
        log.debug("UserController :: The result of the {} method whose arguments were {} is {}",joinPoint.getSignature().getName(),joinPoint.getArgs(), Convertion.objectToJsonString(obj));
        return obj;
    }
}
