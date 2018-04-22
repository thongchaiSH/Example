package com.example.demo.spring.configuration;


import com.example.demo.constant.ApplicationConstant;
import org.apache.catalina.SessionListener;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) {
        // Create the 'root' Spring application context
//        System.setProperty("rootPath", container.getRealPath("/"));
//        System.setProperty("applicationName", ApplicationConstant.APPLICATION_NAME);
               // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ApplicationConfiguration.class);
        ctx.setServletContext(container);

        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        container.addListener(new RequestContextListener());

        container.addListener(new RequestContextListener());
//        container.addListener(new SessionListener());

    }
}
