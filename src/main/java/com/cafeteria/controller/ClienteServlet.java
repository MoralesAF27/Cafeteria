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
import com.cafeteria.service.ProductoService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cliente/dashboard")
public class ClienteServlet extends HttpServlet {
    private final PedidoService pedidoService = new PedidoService();
    private final ProductoService productoService = new ProductoService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        // Obtener los últimos pedidos del cliente
        List<Pedido> ultimosPedidos = pedidoService.obtenerPedidosPorUsuario(usuario.getUsuarioId())
                .stream()
                .limit(5)
                .toList();
        
        // Obtener productos recomendados
        List<Producto> productosRecomendados = productoService.obtenerProductosDisponibles()
                .stream()
                .limit(4)
                .toList();
        
        request.setAttribute("ultimosPedidos", ultimosPedidos);
        request.setAttribute("productosRecomendados", productosRecomendados);
        
        request.getRequestDispatcher("/WEB-INF/views/cliente/dashboard.jsp").forward(request, response);
    }
}
