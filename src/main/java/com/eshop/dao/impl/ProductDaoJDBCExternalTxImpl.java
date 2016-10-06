package com.eshop.dao.impl;

import com.eshop.dao.ProductDao;
import com.eshop.dao.exception.DaoSystemException;
import com.eshop.dao.exception.NoSuchEntityException;
import com.eshop.entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBCExternalTxImpl implements ProductDao{

    public static final String SELECT_ALL_SQL = "SELECT id, name FROM Products";
    public static final String SELECT_BY_ID_SQL = "SELECT name FROM Products WHERE id = ?";

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Product selectById(int id) throws NoSuchEntityException, DaoSystemException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Connection conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("No Product for i == " + id);
            }
            return new Product(id,rs.getString("Name"));
        } catch (SQLException e) {
            throw new DaoSystemException("DB Exception", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignore) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ignore) {}
            }
        }
    }
    @Override
    public List<Product> selectAll() throws DaoSystemException {
        Statement stmt = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                products.add(new Product(rs.getInt(1), rs.getString(2)));
            }
            return products;
        } catch (SQLException e) {
            throw new DaoSystemException("DB Exception", e);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException ignore) {}
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignore) {}
        }
    }
}
