/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.controller;

/**
 *
 * @author andre
 */
import com.cafeteria.service.ReporteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/reporte")
public class ReporteServlet extends HttpServlet {
    private final ReporteService reporteService = new ReporteService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "sales";
        }
        
        try {
            switch (action) {
                case "inventory":
                    showInventoryReport(request, response);
                    break;
                case "products":
                    showProductsReport(request, response);
                    break;
                default:
                    showSalesReport(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void showSalesReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fechaInicio = request.getParameter("fecha_inicio");
        String fechaFin = request.getParameter("fecha_fin");
        
        if (fechaInicio == null || fechaFin == null) {
            // Establecer fechas por defecto (últimos 30 días)
            fechaInicio = java.time.LocalDate.now().minusDays(30).toString();
            fechaFin = java.time.LocalDate.now().toString();
        }
        
        List<Map<String, Object>> ventas = reporteService.obtenerVentasPorPeriodo(fechaInicio, fechaFin);
        
        request.setAttribute("ventas", ventas);
        request.setAttribute("fechaInicio", fechaInicio);
        request.setAttribute("fechaFin", fechaFin);
        request.getRequestDispatcher("/WEB-INF/views/admin/reportes/ventas.jsp").forward(request, response);
    }
    
    private void showProductsReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fechaInicio = request.getParameter("fecha_inicio");
        String fechaFin = request.getParameter("fecha_fin");
        
        if (fechaInicio == null || fechaFin == null) {
            // Establecer fechas por defecto (últimos 30 días)
            fechaInicio = java.time.LocalDate.now().minusDays(30).toString();
            fechaFin = java.time.LocalDate.now().toString();
        }
        
        List<Map<String, Object>> productos = reporteService.obtenerProductosMasVendidos(fechaInicio, fechaFin);
        
        request.setAttribute("productos", productos);
        request.setAttribute("fechaInicio", fechaInicio);
        request.setAttribute("fechaFin", fechaFin);
        request.getRequestDispatcher("/WEB-INF/views/admin/reportes/productos.jsp").forward(request, response);
    }
    
    private void showInventoryReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Map<String, Object>> inventarioBajo = reporteService.obtenerInventarioBajo();
        request.setAttribute("inventarioBajo", inventarioBajo);
        request.getRequestDispatcher("/WEB-INF/views/admin/reportes/inventario.jsp").forward(request, response);
    }
}
