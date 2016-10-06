package com.eshop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

public class SimpleLogger {

    /**
     * Замеряет время работы метода
     * @param call это ссылка на вызываемый метод
     *  ProceedingJoinPoint - аналог FilterChain
     */
    public Object log(ProceedingJoinPoint call) throws Throwable{
        try{
            return call.proceed(); // работай
        } finally {
            // это произойдет после того как метод отработает
            System.out.println("ASPECT.LOGGER: " + call.toShortString() + " called. ARGS == "
                                + Arrays.toString(call.getArgs()));
        }
    }
}
