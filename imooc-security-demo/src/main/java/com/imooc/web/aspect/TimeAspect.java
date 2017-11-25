package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.util.Date;

//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start!");
        long start = new Date().getTime();
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("参数："+arg);
        }
        Object proceed = pjp.proceed();
        System.out.println("time aspect: " + (new Date().getTime()-start));
        System.out.println("time aspect finish");
        return proceed;
    }

}
