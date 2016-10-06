package com.eshop.dao.impl;

import com.eshop.dao.ProductDao;
import com.eshop.dao.exception.DaoSystemException;
import com.eshop.dao.exception.NoSuchEntityException;
import com.eshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBCImpl implements ProductDao {

    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/eshop?user=root&password=root";

    public static final String SELECT_ALL_SQL = "SELECT id, name FROM Products";
    public static final String SELECT_BY_ID_SQL = "SELECT name FROM Products WHERE id = ?";

    public Product selectById(int id) throws NoSuchEntityException, DaoSystemException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL);
//            BEGIN TRANSACTION
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("No Product for i == " + id);
            }
            Product product = new Product(id,rs.getString("Name"));
//            COMMIT TRANSACTION
            conn.commit();
            return product;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ignore) {}
            throw new DaoSystemException("DB Exception", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignore) {}
            }
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(JDBC_URL);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                products.add(new Product(rs.getInt(1), rs.getString(2)));
            }
//            если транзакция была только на чтение то rollback() + отработает быстрее
            conn.rollback();
            return products;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ignore) {}
            throw new DaoSystemException("DB Exception", e);
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ignore) {}
        }
    }

//    public static void main(String[] args) throws DaoSystemException, NoSuchEntityException {
//        ProductDaoJDBCImpl dao = new ProductDaoJDBCImpl();
//            System.out.println(dao.selectById(1));
//    }
}

