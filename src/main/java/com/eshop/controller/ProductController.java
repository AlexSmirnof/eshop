package com.eshop.controller;

import com.eshop.dao.ProductDao;
import com.eshop.dao.exception.DaoSystemException;
import com.eshop.dao.exception.NoSuchEntityException;
import com.eshop.entity.Product;
import com.eshop.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductController extends DependencyInjectionServlet {

    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    public static final String PAGE_OK = "product.jsp";
    public static final String PAGE_ERROR = "error.jsp";

//    private ProductDao productDao = new ProductDaoMock();
    @Inject("productDao")
    private static ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                System.out.println("+++ ID == "+id);
                Product model = productDao.selectById(id);
                System.out.println("+++ ID == "+id);
                req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
                //OK
                req.getRequestDispatcher(PAGE_OK).forward(req,resp);
                return;
            } catch (NumberFormatException | DaoSystemException | NoSuchEntityException | IOException e) {
                System.err.println(e.toString());
            }
        }
        //FAIL
        resp.sendRedirect(PAGE_ERROR);
    }
}
