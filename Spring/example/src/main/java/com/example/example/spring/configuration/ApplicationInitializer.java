//package com.example.example.spring.configuration;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.request.RequestContextListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//public class ApplicationInitializer implements WebApplicationInitializer {
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        System.setProperty("rootPath", servletContext.getRealPath("/"));
//        System.setProperty("applicationName", "Example");
//
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(ApplicationConfiguration.class);
//        ctx.setServletContext(servletContext);
//
//        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
//
//        servlet.setLoadOnStartup(1);
//        servlet.addMapping("/");
//
//        servletContext.addListener(new RequestContextListener());
//    }
//}
