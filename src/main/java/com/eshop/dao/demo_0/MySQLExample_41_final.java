package com.eshop.dao.demo_0;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MySQLExample_41_final {
    public static void main(String[] args) throws IOException {

//        ClassLoader обходит весь classpath, кот не всегда наход только в файловой системе, м б в интернете
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ClassLoader loader2 = MySQLExample_41_final.class.getClassLoader();

        System.out.println(loader == loader2);

        Enumeration<URL> drivers = loader.getResources("META-INF/services/"+"java.sql.Driver");
        while (drivers.hasMoreElements()) {
            System.out.println(drivers.nextElement());
        }
    }
// "!" в пути значит что мы вошли jar(zip) архив
}
