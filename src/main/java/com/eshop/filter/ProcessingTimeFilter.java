package com.eshop.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Этот фильтр должен стоять первым в цепочке, чтобы правильно замерить время
 */
public class ProcessingTimeFilter implements Filter{

    public ProcessingTimeFilter() {
        System.out.println(">> ProcessingTimeFilter - NEW");
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(">> ProcessingTimeFilter - init");
        filterConfig.getServletContext().log("===== L_O_G_G_I_N_G =====");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        long inTime = System.nanoTime();
        filterChain.doFilter(request,servletResponse); // передаст вызов другим звеньям и вернется снова как рекурсия
        long outTime = System.nanoTime();
        System.out.println(">> ProcessingTimeFilter: dT = " + (outTime - inTime));
        System.out.println();
    }

    @Override
    public void destroy() {
        System.out.println(">> ProcessingTimeFilter - destroy");
    }
}
