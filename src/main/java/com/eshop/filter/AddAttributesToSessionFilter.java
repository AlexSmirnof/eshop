package com.eshop.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;


public class AddAttributesToSessionFilter extends BaseFilter{

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = servletRequest.getSession(true);
        Enumeration<String> iter = filterConfig.getInitParameterNames();
        while (iter.hasMoreElements()){
            String initParamName = iter.nextElement();
            String initParameterValue = filterConfig.getInitParameter(initParamName);
            session.setAttribute(initParamName,initParameterValue);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
