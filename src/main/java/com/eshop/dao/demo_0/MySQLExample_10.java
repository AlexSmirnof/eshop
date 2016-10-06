package com.eshop.dao.demo_0;

import com.mysql.jdbc.JDBC4Connection;

import java.sql.SQLException;
import java.util.Properties;

public class MySQLExample_10 {

    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test?user=root&password=root";

    public static void main(String[] args) throws SQLException {

        com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
        try(com.mysql.jdbc.JDBC4Connection conn = (JDBC4Connection) driver.connect(JDBC_URL,new Properties())){
            System.out.println("conn = " + conn);
        }

        java.sql.Driver driver0 = new com.mysql.jdbc.Driver();
        System.out.println(driver0.acceptsURL("jdbc:SUPER_DB"));   //  =>  false    проверяет только формат
        System.out.println(driver0.acceptsURL(JDBC_URL));          //  =>  true
        try (java.sql.Connection conn = driver0.connect(JDBC_URL, new Properties())){
            System.out.println("conn = " + conn);
        }


    }
}
