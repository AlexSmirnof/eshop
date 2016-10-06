package com.eshop.dao.demo_0;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class MySQLExample_30 {

    public static final String SUPER_DRIVER = "com.eshop.dao.demo_0.SuperDbDriver";

    static {
        try {
//            появилось в версии JDBC 2.0
            DriverManager.registerDriver(new SuperDbDriver());
        } catch (SQLException e) {
            throw new RuntimeException("Can`t register Driver!");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {

//        Загружает байткод класса в JVM и инициализирует static , но не создает экземпляр
        Class.forName("com.mysql.jdbc.Driver").getName();

        Enumeration<Driver> iter = DriverManager.getDrivers();
        while (iter.hasMoreElements()) {
            Driver driver = iter.nextElement();
            System.out.println("driver = " + driver);
        }

    }
}
