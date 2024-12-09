package com.acc.user.aop;

import com.acc.user.util.Convertion;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class UserServicelogs {
    @Pointcut("@within(com.acc.user.customAnnotarion.Userlog) || @annotation(com.acc.user.customAnnotarion.Userlog)")
    public void annotationPointcut(){

    }

    @Around("annotationPointcut()")
    public Object serviceLogs(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("UserService :: calling {} method and arguments were {}",joinPoint.getSignature().getName(),joinPoint.getArgs());
        Object obj=joinPoint.proceed();
        log.info("UserService :: The result of the {} method whose arguments were {} is {}",joinPoint.getSignature().getName(),joinPoint.getArgs(), Convertion.objectToJsonString(obj));
        return obj;
    }

    @After("annotationPointcut()")
    public void serviceAfterlogs(){
        
    }


}
