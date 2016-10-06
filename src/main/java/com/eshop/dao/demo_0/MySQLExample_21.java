package com.eshop.dao.demo_0;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class MySQLExample_21 {

    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test?user=root&password=root";

    public static void main(String[] args) throws SQLException {

        DriverManager.registerDriver(new SuperDbDriver());

        Enumeration<Driver> iter = DriverManager.getDrivers();
        while (iter.hasMoreElements()) {
            Driver driver = iter.nextElement();
            System.out.println("driver = " + driver);
        }
        try(Connection conn = DriverManager.getConnection(JDBC_URL)){
            System.out.println("conn = " + conn);
        }
        try(Connection conn = DriverManager.getConnection("jdbc:SUPER_DB://")){
            System.out.println("conn = " + conn);
        }


    }

}
