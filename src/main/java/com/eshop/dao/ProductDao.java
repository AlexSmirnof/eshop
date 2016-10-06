package com.eshop.dao;

import com.eshop.dao.exception.DaoSystemException;
import com.eshop.dao.exception.NoSuchEntityException;
import com.eshop.entity.Product;

import java.util.List;

public interface ProductDao {

    Product selectById(int id) throws DaoSystemException, NoSuchEntityException;

    List<Product> selectAll() throws DaoSystemException;
}
