package com.eshop.dao.meta_info_db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MetaDataExample {

    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test?user=root&password=root";

    public static void main(String[] args) throws SQLException {
        try (Connection conn =DriverManager.getConnection(JDBC_URL)){

            DatabaseMetaData metaData = conn.getMetaData();
            System.out.printf("IsolationLevel: %s%n", conn.getTransactionIsolation() == Connection.TRANSACTION_REPEATABLE_READ);
            System.out.printf("Catalogs: %s%n",metaData.getCatalogs());
            System.out.printf("Schemas: %s%n", metaData.getSchemas());
            System.out.printf("Tables: %s%n", metaData.getTables(null, null, null, null));
            System.out.printf("PrimaryKeys: %s%n",metaData.getPrimaryKeys(null, null, "users"));
            System.out.printf("TypeInfo: %s%n",metaData.getTypeInfo());
            System.out.printf("TableTypes: %s%n",metaData.getTableTypes());
            System.out.printf("Procedures: %s%n",metaData.getProcedures(null, null, null));
            System.out.printf("Functions: %s%n",metaData.getFunctions(null,null,null));
            System.out.printf("SQLStateType = %d%n",metaData.getSQLStateType());
            System.out.printf("ResultSetHoldability = %d%n", metaData.getResultSetHoldability());
        }
    }
}
