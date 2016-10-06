package com.eshop.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class MyHttpSessionActivationListener implements HttpSessionActivationListener {
    public MyHttpSessionActivationListener() {
        System.out.println(">>>MyHttpActivationListener - NEW");
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent event) {
        System.out.println(">>>HttpSession - will passivate, id = " + event.getSession().getId());
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent event) {
        System.out.println(">>>HttpSession - did activate, id = " + event.getSession().getId());
    }
}
