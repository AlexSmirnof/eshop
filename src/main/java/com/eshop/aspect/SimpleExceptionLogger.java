package com.eshop.aspect;

import org.aspectj.lang.JoinPoint;

public class SimpleExceptionLogger {

    /**
     * Этот метод будет вызываться каждый раз как только в системе появляется исключение в любом месте
     * не прекращает полет исключения
     * JoinPoint - действует после вызова метода в отличии от ProceedingJoinPoint кот охватывает вызов метода
     */
    public void logException(JoinPoint point, Throwable t){
//        Method method = ((MethodSignature)point.getSignature()).getMethod();
//        Object[] args = point.getArgs();
//        Object target = point.getTarget();
        System.out.println("ASPECT.EXCEPTION-LOGGER: " + t.getMessage());
    }
}
