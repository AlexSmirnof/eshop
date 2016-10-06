package com.eshop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class SimpleProfiler {
    /**
     * Вызывается после метода и выводит его арг-ты
     * @param call - это вызов метода
     * @param id - у этих методов должен быть аргумент один(id) типа int
     */
    public Object profile(ProceedingJoinPoint call, int id) throws Throwable{
        long t0 = System.nanoTime();
        try{
            return call.proceed();//продолжать,происходить
        } finally {
            long t1 = System.nanoTime();
            System.out.println("ASPECT.PROFILER: "
                    + call.toShortString() + ", dT: " + (t1 - t0)/1000);
        }
    }
}
