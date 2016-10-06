package com.eshop.controller;

import com.eshop.dao.ProductDao;
import com.eshop.dao.TransactionManager;
import com.eshop.dao.UnitOfWork;
import com.eshop.dao.exception.DaoException;
import com.eshop.dao.exception.DaoSystemException;
import com.eshop.dao.exception.NoSuchEntityException;
import com.eshop.entity.Product;
import com.eshop.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductAddToBucketControllerExternalTx extends DependencyInjectionServlet {
    private static final String PARAM_ID = "id";
    private static final String PAGE_ERROR = "productAll.do";
    private static final String PRODUCTS_IN_BUCKET = "productsInBucket";

    @Inject("txManager")
    private static TransactionManager txManager;
    @Inject("productDao")
    private static ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try{
                Integer id = Integer.valueOf(idStr);
                Product product = txManager.doInTransaction(new UnitOfWork<Product, DaoException>() {
                    @Override
                    public Product doInTx() throws DaoException {
                        return productDao.selectById(id);
                    }
                });
                HttpSession session = req.getSession(true);
                Map<Product,Integer> oldBucket = (Map<Product, Integer>) session.getAttribute(PRODUCTS_IN_BUCKET);
                if (oldBucket == null) {
                    session.setAttribute(PRODUCTS_IN_BUCKET, Collections.singletonMap(product, 1));
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
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (DaoException e) {
                e.printStackTrace();
            }
//            FAIL
            resp.sendRedirect(PAGE_ERROR);
        }
    }
}
