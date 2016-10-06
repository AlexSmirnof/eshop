package com.eshop.dao.impl;

import com.eshop.dao.ProductDao;
import com.eshop.dao.exception.DaoSystemException;
import com.eshop.dao.exception.NoSuchEntityException;
import com.eshop.entity.Product;

import java.util.List;

public class ProductDaoSpringJdbcTemplate implements ProductDao{
    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        return null;
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        return null;
    }
}
