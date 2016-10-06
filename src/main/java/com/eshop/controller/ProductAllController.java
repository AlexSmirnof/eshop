package com.eshop.controller;

import com.eshop.dao.ProductDao;
import com.eshop.dao.exception.DaoSystemException;
import com.eshop.dao.impl.ProductDaoJDBCImpl;
import com.eshop.dao.impl.ProductDaoMock;
import com.eshop.entity.Product;
import com.eshop.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductAllController extends DependencyInjectionServlet {
    private static final String ATTRIBUTE_MODEL_TO_VIEW = "productsAll";
    private static final String PAGE_OK = "productAll.jsp";
    private static final String PAGE_ERROR = "error.jsp";

//    private ProductDao productDao = new ProductDaoMock();
    @Inject("productDao")
    private static ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Product> model = productDao.selectAll();
            req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
//            OK
            RequestDispatcher dispatcher = req.getRequestDispatcher(PAGE_OK);
            dispatcher.forward(req,resp);
            return;
        } catch (DaoSystemException e) {
            e.printStackTrace();
        }
//        FAIL
        resp.sendRedirect(PAGE_ERROR);
    }

}
