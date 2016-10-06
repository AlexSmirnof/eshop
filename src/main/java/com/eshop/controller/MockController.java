package com.eshop.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class MockController extends HttpServlet {
    private ServletConfig config;

    public MockController() {
        System.out.println(">>>MockController - NEW");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config = config;
        System.out.println(">>>MockController - INIT");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        Enumeration iter = req.getSession().getAttributeNames();
        while (iter.hasMoreElements()) {
            sb.append(iter.nextElement()).append(", ");
        }
        resp.getWriter().write("Hello World Servlet!!!\ninit-params: " +
                                config.getInitParameter("init")+
                                "\nattrs: "+ sb.toString());

    }
}
