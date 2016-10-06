package com.eshop.dao.impl;

import com.eshop.dao.ProductDao;
import com.eshop.dao.exception.NoSuchEntityException;
import com.eshop.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductDaoMock implements ProductDao {
    private final Map<Integer,Product> memory = new ConcurrentHashMap<>();

    public ProductDaoMock() {
        memory.put(1,new Product(1,"Bread"));
        memory.put(2,new Product(2,"Paper"));
        memory.put(3,new Product(3,"Sugar"));
    }

    @Override
    public Product selectById(int id) throws NoSuchEntityException {
        Product product = memory.get(id);
        if (product == null) {
            throw new NoSuchEntityException("No product for id == "+ id);
        }
        return product;
    }
    @Override
    public List<Product> selectAll() {
        return new ArrayList<>(memory.values());
    }
}
