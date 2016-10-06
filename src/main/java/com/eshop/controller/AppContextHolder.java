package com.eshop.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Синглетонное хранилище AppContext`ов
 * Необ-мо для того чтобы каждый из потомков DependencyInjectionServlet
 * не создавал свой AppContext, но использовал уже сущ-ий и разделяемый с др сервлетами.
 * Хотя может хранить мн-во контекстов - по одному для каждого xml
 * но в нашей системе будет один на все сервлеты
 */
public class AppContextHolder {

    private static final Map<String,ApplicationContext> pathToAppContextRepo = new HashMap<>();

    static synchronized final ApplicationContext getPathXmlApplicationContext(String path){
        if (!pathToAppContextRepo.containsKey(path)){
            pathToAppContextRepo.put(path,new ClassPathXmlApplicationContext(path));
        }
        return pathToAppContextRepo.get(path);
    }

}
