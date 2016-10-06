package com.eshop.controller;

import com.eshop.entity.MockEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MVCMockController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        request
        req.setAttribute("reqAtr",new MockEntity());
//      session
        req.getSession().setAttribute("sesAtr", new MockEntity());
//        servlet context
        req.getSession().getServletContext().setAttribute("appAtr",new MockEntity());

        req.setAttribute("test","request");
        req.getSession().setAttribute("test","session");
        req.getSession().getServletContext().setAttribute("test","application");

        req.getRequestDispatcher("mvcMock.jsp").forward(req,resp);
    }
}
