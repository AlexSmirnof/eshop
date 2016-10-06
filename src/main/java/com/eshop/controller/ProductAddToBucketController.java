package com.eshop.controller;

import com.eshop.dao.ProductDao;
import com.eshop.dao.exception.DaoSystemException;
import com.eshop.dao.exception.NoSuchEntityException;
import com.eshop.dao.impl.ProductDaoMock;
import com.eshop.entity.Product;
import com.eshop.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductAddToBucketController extends DependencyInjectionServlet {
    private static final String PARAM_ID = "id";
    private static final String PAGE_ERROR = "productAll.do";
    private static final String PRODUCTS_IN_BUCKET = "productsInBucket";

//    private ProductDao productDao = new ProductDaoMock();
    @Inject("productDao")
    private static ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try{
                Integer id = Integer.valueOf(idStr);
                Product product = productDao.selectById(id);

                HttpSession session = req.getSession(true);
                Map<Product,Integer> oldBucket = (Map<Product, Integer>) session.getAttribute(PRODUCTS_IN_BUCKET);
                if (oldBucket == null) {
                    session.setAttribute(PRODUCTS_IN_BUCKET, Collections.singletonMap(product,1));
                } else {
                    LinkedHashMap<Product, Integer> newBucket = new LinkedHashMap<>(oldBucket);
                    if (!newBucket.containsKey(product)) {
                        newBucket.put(product, 1);
                    } else {
                        newBucket.put(product, newBucket.get(product) + 1);
                    }
                    session.setAttribute(PRODUCTS_IN_BUCKET, Collections.unmodifiableMap(newBucket));
                }
//                OK
                String newLocation = "product.do?id=" + id;
                resp.sendRedirect(newLocation);
                return;
            } catch (NumberFormatException | DaoSystemException | NoSuchEntityException e) {
                e.printStackTrace();
            }
//            FAIL
            resp.sendRedirect(PAGE_ERROR);
        }
    }
}
