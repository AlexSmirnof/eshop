package com.eshop.controller;

import com.eshop.inject.FieldReflector;
import com.eshop.inject.Inject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.List;

public class DependencyInjectionServlet extends HttpServlet {
    private static final String APP_CTX_PATH = "contextConfigLocation";

//    этот метод будет также вызываться у потомков new ProductController().init()
//    т е это виртуальный метод
    @Override
    public final void init() throws ServletException {
        String appCtxPath = this.getServletContext().getInitParameter(APP_CTX_PATH);
        System.out.println(">>>LOAD " + APP_CTX_PATH + " -> " + appCtxPath);
        if (appCtxPath == null) {
            System.err.println("I need init param" + APP_CTX_PATH);
            throw new ServletException(APP_CTX_PATH + " init param == null");
        }
        try {
//            load AppContext
            ApplicationContext appCtx = AppContextHolder.getPathXmlApplicationContext(appCtxPath);
//            then inject from AppContext to all marked by @Inject fields
            List<Field> allFields = FieldReflector.collectUpTo(this.getClass(), DependencyInjectionServlet.class);
            List<Field> injectFields = FieldReflector.filterInject(allFields);

            for (Field field : injectFields) {
                field.setAccessible(true);
                Inject annotation = field.getAnnotation(Inject.class);//получаем у поля аннотацию
                System.out.println("I find method marked by @Inject: " + field);
                String beanName = annotation.value(); // получаем у аннотации значение поля value
                System.out.println("I must instantiate and inject '" + beanName + "'");
                Object bean = appCtx.getBean(beanName);//получаем bean по  имени из контекста указ в xml файле
                System.out.println("Instantiation - OK: '" + beanName + "'");
                if (beanName == null) {
                    throw new ServletException("There isn`t bea with name '" + beanName + "'");
                }
                field.set(this, bean);// сохраняем полученный bean в данное поле на экзумпляре this
                                      // this - так как этот метод будет вызываться у new ProductController().init()
            }
        } catch (Exception e) {
            throw new ServletException("Can`t inject from " + APP_CTX_PATH, e);
        }
    }
}
