package com.cafeteria;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppConfig implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("appName", "Sistema de Gestión para Cafetería");
        context.setAttribute("appVersion", "1.0.0");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Limpieza al cerrar la aplicación
    }
}