package com.eshop.dao.demo_1_stmts;

import java.sql.*;

public class Callble_Test {
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/world?user=root&password=root";

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DriverManager.getConnection(JDBC_URL)){

            String call = "{call get_cityName(?,?)}";
            CallableStatement callableStatement = conn.prepareCall(call);

            callableStatement.setInt(1,10);

            callableStatement.registerOutParameter(2, Types.VARCHAR);

            System.out.println(callableStatement.execute());

            String city = callableStatement.getString(2);

            System.out.println("RESULT: " + city);
        }
    }
}
