package com.xjx.security.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author XJX
 * @Date 2020/7/1 11:26
 * @Version 1.0
 */
@Slf4j
//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.xjx.security.web.controller.UserController.*(..)) && (@annotation(com.fasterxml.jackson.annotation.JsonView))")
    public Object handlerControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("time aspect start");

        Object[] args = joinPoint.getArgs();
        String sts = Arrays.toString(args );

        log.info("str:{}",sts);
        for (Object arg:args){
            log.info("arg is:{}",arg);
        }
        long startTime = new Date().getTime();

        Object proceed = joinPoint.proceed();

        log.info("time aspect 耗时:{}",(new Date().getTime() - startTime));
        log.info("time aspect end");

        return proceed;
    }

}
