package com.eshop.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieMockController extends HttpServlet {
    private static final String COOKIE_NAME = "counter";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie fromClient = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    fromClient = cookie;
                    break;
                }
            }
        }
        if (fromClient == null) {
            resp.addCookie(new Cookie(COOKIE_NAME,"" + 1));
            resp.getWriter().write("You visit this page: 1 time");
        } else{
            int visitCount = Integer.valueOf(fromClient.getValue());
            resp.addCookie(new Cookie(COOKIE_NAME, "" + ++visitCount));
            resp.getWriter().write("You visit this page: " + visitCount + " times");
        }
    }
}
