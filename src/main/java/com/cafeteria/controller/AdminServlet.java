/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.controller;

/**
 *
 * @author andre
 */
import com.cafeteria.service.PedidoService;
import com.cafeteria.service.ReporteService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/dashboard")
public class AdminServlet extends HttpServlet {
    private final PedidoService pedidoService = new PedidoService();
    private final ReporteService reporteService = new ReporteService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener estadísticas para el dashboard
        long pedidosPendientes = pedidoService.obtenerTodosPedidos().stream()
                .filter(p -> p.getEstadoId() == 1) // Estado 1 = Pendiente
                .count();
        
        List<Map<String, Object>> inventarioBajo = reporteService.obtenerInventarioBajo();
        
        // Obtener ventas de hoy
        String hoy = java.time.LocalDate.now().toString();
        List<Map<String, Object>> ventasHoy = reporteService.obtenerVentasPorPeriodo(hoy, hoy);
        double totalVentasHoy = ventasHoy.stream()
                .mapToDouble(v -> (Double) v.get("total"))
                .sum();
        
        request.setAttribute("pedidosPendientes", pedidosPendientes);
        request.setAttribute("inventarioBajo", inventarioBajo.size());
        request.setAttribute("ventasHoy", totalVentasHoy);
        
        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
    }
}
