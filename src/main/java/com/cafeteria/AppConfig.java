package com.cafeteria;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

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