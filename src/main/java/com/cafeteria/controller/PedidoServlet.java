/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.controller;

/**
 *
 * @author andre
 */
import com.cafeteria.model.DetallePedido;
import com.cafeteria.model.Pedido;
import com.cafeteria.service.PedidoService;
import com.cafeteria.service.ProductoService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/pedido")
public class PedidoServlet extends HttpServlet {
    private final PedidoService pedidoService = new PedidoService();
    private final ProductoService productoService = new ProductoService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (action == null) {
            action = "list";
        }
        
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "view":
                    viewPedido(request, response);
                    break;
                case "updateStatus":
                    updateStatus(request, response);
                    break;
                default:
                    if (usuario.getRolId() == 1 || usuario.getRolId() == 2) {
                        listAllPedidos(request, response);
                    } else {
                        listUserPedidos(request, response, usuario.getUsuarioId());
                    }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        try {
            switch (action) {
                case "create":
                    createPedido(request, response, usuario.getUsuarioId());
                    break;
                default:
                    if (usuario.getRolId() == 1 || usuario.getRolId() == 2) {
                        listAllPedidos(request, response);
                    } else {
                        listUserPedidos(request, response, usuario.getUsuarioId());
                    }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void listAllPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pedidos", pedidoService.obtenerTodosPedidos());
        request.getRequestDispatcher("/WEB-INF/views/admin/pedidos/list.jsp").forward(request, response);
    }
    
    private void listUserPedidos(HttpServletRequest request, HttpServletResponse response, int usuarioId)
            throws ServletException, IOException {
        request.setAttribute("pedidos", pedidoService.obtenerPedidosPorUsuario(usuarioId));
        request.getRequestDispatcher("/WEB-INF/views/cliente/pedidos/list.jsp").forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("productos", productoService.obtenerProductosDisponibles());
        request.getRequestDispatcher("/WEB-INF/views/cliente/pedidos/form.jsp").forward(request, response);
    }
    
    private void viewPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        request.setAttribute("pedido", pedido);
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario.getRolId() == 1 || usuario.getRolId() == 2) {
            request.getRequestDispatcher("/WEB-INF/views/admin/pedidos/detalle.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/views/cliente/pedidos/detalle.jsp").forward(request, response);
        }
    }
    
    private void createPedido(HttpServletRequest request, HttpServletResponse response, int usuarioId)
            throws ServletException, IOException {
        // Lógica para crear un nuevo pedido
        // Esto es un ejemplo simplificado
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(usuarioId);
        pedido.setEstadoId(1); // Estado inicial: Pendiente
        pedido.setTotal(Double.parseDouble(request.getParameter("total")));
        pedido.setNotas(request.getParameter("notas"));
        pedido.setDireccionEntrega(request.getParameter("direccion"));
        pedido.setMetodoPago(request.getParameter("metodo_pago"));
        
        // Aquí deberías procesar los detalles del pedido
        List<DetallePedido> detalles = new ArrayList<>();
        // ... lógica para obtener los detalles del formulario
        
        int pedidoId = pedidoService.crearPedido(pedido, detalles);
        
        if (pedidoId > 0) {
            response.sendRedirect("pedido?action=view&id=" + pedidoId);
        } else {
            request.setAttribute("error", "Error al crear el pedido");
            showNewForm(request, response);
        }
    }
    
    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pedidoId = Integer.parseInt(request.getParameter("id"));
        int estadoId = Integer.parseInt(request.getParameter("estado_id"));
        
        if (pedidoService.actualizarEstadoPedido(pedidoId, estadoId)) {
            response.sendRedirect("pedido?action=view&id=" + pedidoId);
        } else {
            request.setAttribute("error", "Error al actualizar el estado del pedido");
            viewPedido(request, response);
        }
    }
}
