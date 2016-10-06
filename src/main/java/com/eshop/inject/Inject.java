package com.eshop.inject;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)//SOURCE(только в исх коде *.java), CLASS(в bytecode *.class)
public @interface Inject {
    public String value();  //value - это знач по умолчанию => его можно не указывать при объявлении
}
