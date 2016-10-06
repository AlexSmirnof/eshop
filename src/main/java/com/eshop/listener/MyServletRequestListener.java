package com.eshop.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener {
    public MyServletRequestListener() {
        System.out.println(">>>ServletRequestListener - NEW");
    }
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println(">>>ServletrequestListener - DESTROY");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println(">>>ServletrequestListener - REQUEST: "+ servletRequestEvent.getServletRequest());
    }
}
