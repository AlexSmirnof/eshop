package com.eshop.dao.demo_0;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;

public class MySQLExample_51_conn_time {

    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test?user=root&password=root";

    public static void main(String[] args) throws SQLException {

        Driver driver = new com.mysql.jdbc.Driver();
        for (int i = 0; i < 200; i++) {
            long t1 = System.nanoTime();
            try(Connection conn = driver.connect("",null)){}
            long t2 = System.nanoTime();
            System.out.printf("%,10d\n",t2 -t1);


        }

    }

}
