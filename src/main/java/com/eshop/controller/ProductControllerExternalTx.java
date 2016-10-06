package com.eshop.controller;

import com.eshop.dao.ProductDao;
import com.eshop.dao.TransactionManager;
import com.eshop.dao.UnitOfWork;
import com.eshop.dao.exception.DaoException;
import com.eshop.entity.Product;
import com.eshop.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductControllerExternalTx extends DependencyInjectionServlet {

    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    public static final String PAGE_OK = "product.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    @Inject("txManager")
    private static TransactionManager txManager;

    @Inject("productDao")
    private static ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
//                IRunnable и ICallable реализуют шаблон Command`a - это когда у нас есть:   1)ссылка для вызова метода;
//                                                                                           2)аргументы для метода
//                                                                                           3) НО МОМЕНТ ВЫЗОВА (КОГДА) метода НЕ определен
                Product model = txManager.doInTransaction(new UnitOfWork<Product, DaoException>() {
                    @Override
                    public Product doInTx() throws DaoException {
                        return productDao.selectById(id);
                    }
                });
                req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
                //OK
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        //FAIL
        resp.sendRedirect(PAGE_ERROR);
    }
}


