package com.eshop.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class RequestInfoFilter extends BaseFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();
        String queryString = request.getQueryString();
        String protocol = request.getProtocol();
        System.out.printf(">>>RequestInFoFilter: method=%s%n\t address=%s%n\t query=%s%n\t protocol=%s",method,remoteAddr,queryString,protocol);
        System.out.println();

        chain.doFilter(request,response);

    }
}
