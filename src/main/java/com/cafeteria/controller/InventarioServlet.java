/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.controller;

/**
 *
 * @author andre
 */
import com.cafeteria.model.Inventario;
import com.cafeteria.service.InventarioService;
import com.cafeteria.service.ProductoService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inventario")
public class InventarioServlet extends HttpServlet {
    private final InventarioService inventarioService = new InventarioService();
    private final ProductoService productoService = new ProductoService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteInventario(request, response);
                    break;
                case "lowStock":
                    showLowStock(request, response);
                    break;
                default:
                    listInventario(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            switch (action) {
                case "insert":
                    insertInventario(request, response);
                    break;
                case "update":
                    updateInventario(request, response);
                    break;
                default:
                    listInventario(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void listInventario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("inventario", inventarioService.obtenerTodoInventario());
        request.getRequestDispatcher("/WEB-INF/views/admin/inventario/list.jsp").forward(request, response);
    }
    
    private void showLowStock(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("inventarioBajo", inventarioService.obtenerInventarioBajo());
        request.getRequestDispatcher("/WEB-INF/views/admin/inventario/alertas.jsp").forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("productos", productoService.obtenerTodosProductos());
        request.getRequestDispatcher("/WEB-INF/views/admin/inventario/form.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Inventario inventario = inventarioService.obtenerItemPorId(id);
        request.setAttribute("inventario", inventario);
        request.setAttribute("productos", productoService.obtenerTodosProductos());
        request.getRequestDispatcher("/WEB-INF/views/admin/inventario/form.jsp").forward(request, response);
    }
    
    private void insertInventario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Inventario inventario = new Inventario();
        inventario.setProductoId(Integer.parseInt(request.getParameter("producto_id")));
        inventario.setCantidadDisponible(Integer.parseInt(request.getParameter("cantidad_disponible")));
        inventario.setCantidadMinima(Integer.parseInt(request.getParameter("cantidad_minima")));
        
        inventarioService.agregarItemInventario(inventario);
        response.sendRedirect("inventario");
    }
    
    private void updateInventario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Inventario inventario = inventarioService.obtenerItemPorId(id);
        
        inventario.setProductoId(Integer.parseInt(request.getParameter("producto_id")));
        inventario.setCantidadDisponible(Integer.parseInt(request.getParameter("cantidad_disponible")));
        inventario.setCantidadMinima(Integer.parseInt(request.getParameter("cantidad_minima")));
        
        inventarioService.actualizarItemInventario(inventario);
        response.sendRedirect("inventario");
    }
    
    private void deleteInventario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        inventarioService.eliminarItemInventario(id);
        response.sendRedirect("inventario");
    }
}
