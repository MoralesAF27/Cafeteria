/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.controller;

/**
 *
 * @author andre
 */
import com.cafeteria.model.Pedido;
import com.cafeteria.service.PedidoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/empleado/dashboard")
public class EmpleadoServlet extends HttpServlet {
    private final PedidoService pedidoService = new PedidoService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener pedidos pendientes y en preparación
        List<Pedido> pedidosPendientes = pedidoService.obtenerTodosPedidos().stream()
                .filter(p -> p.getEstadoId() <= 2) // Estados 1 (Pendiente) y 2 (En preparación)
                .toList();
        
        request.setAttribute("pedidosPendientes", pedidosPendientes);
        request.getRequestDispatcher("/WEB-INF/views/empleado/dashboard.jsp").forward(request, response);
    }
}
