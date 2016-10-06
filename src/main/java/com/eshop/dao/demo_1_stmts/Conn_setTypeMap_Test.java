package com.eshop.dao.demo_1_stmts;

import java.sql.*;

public class Conn_setTypeMap_Test {

    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test?user=root&password=root";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)){

            String className = Product.class.getName();
            java.util.Map map = conn.getTypeMap();
            map.put("test.Product",Class.forName(className));
            conn.setTypeMap(map);

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT Product FROM Products WHERE id=5;");
            rs.next();
            Product prod = (Product) rs.getObject(2);
            rs.close();
            System.out.println(prod);
        }
    }


    private static class Product implements SQLData{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String sql_type;
        @Override
        public String getSQLTypeName() throws SQLException {
            return sql_type;
        }
        @Override
        public void readSQL(SQLInput stream, String typeName) throws SQLException {
            sql_type = typeName;
            id = stream.readInt();
            name = stream.readString();
        }
        @Override
        public void writeSQL(SQLOutput stream) throws SQLException {
            stream.writeInt(id);
            stream.writeString(name);
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", sql_type='" + sql_type + '\'' +
                    '}';
        }
    }
}
