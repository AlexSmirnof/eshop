package com.eshop.inject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldReflector {
    /**
     * Собирает список полей у класса clazz до предка parent
     * @param clazz
     * @param parent
     * @return
     */
    public static List<Field> collectUpTo(Class<?> clazz, Class<?> parent ){
        ArrayList<Field> result = new ArrayList<>();
        Class<?> current = clazz;
        while(current != parent){
            result.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        return result;
    }

    public static List<Field> filterInject(List<Field> allFields){
        ArrayList<Field> result = new ArrayList<>();
        for (Field field : allFields) {
            Inject annotation = field.getAnnotation(Inject.class);
            if (annotation != null) {
                result.add(field);
            }
        }
        return result;
    }
}
