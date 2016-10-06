package com.eshop.dao.demo_1_stmts;

import java.sql.*;
import java.util.Arrays;

public class DDL_Commit_Test {

    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test?user=root&password=root";

    public static void main(String[] args) throws SQLException {

        int[] ints = null;
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();

//  Exception in thread "main" java.sql.SQLException: Can't call commit when autocommit=true
//            statement.execute("BEGIN");

            statement.addBatch("DROP TABLE IF EXISTS Products;");
            statement.addBatch("CREATE TABLE Products (id INT, name VARCHAR(64));");
            statement.addBatch("INSERT INTO Products (id, name) VALUES('1', 'Bread');");
            statement.addBatch("INSERT INTO Products (id, name) VALUES('2', 'Paper');");
            statement.addBatch("INSERT INTO Products (id, name) VALUES('3', 'Sugar');");
            statement.addBatch("INSERT INTO Products (id, name) VALUES('4', 'Milk');");
//            statement.addBatch("INSERT INTO Products (id, name) VALUES('5', 'Drugs');");
//            statement.addBatch("DROP TABLE Products;");

            ints = statement.executeBatch();
//            statement.execute("COMMIT");
//            statement.execute("ROLLBACK");

            conn.commit();
//            conn.rollback();
            statement.close();

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Products (id, name) VALUES(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,5);
            preparedStatement.setString(2, "Drugs");
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            System.out.println(generatedKeys.getMetaData());


            ResultSet rs = preparedStatement.executeQuery("SELECT id, name FROM Products;");
            while (rs.next()) {
//                rs.absolute(5);
//                rs.deleteRow();
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
            preparedStatement.close();
        }

        System.out.println(Arrays.toString(ints));

    }

}
