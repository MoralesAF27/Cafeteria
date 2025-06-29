/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.controller;

/**
 *
 * @author andre
 */
import com.cafeteria.model.Producto;
import com.cafeteria.service.ProductoService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/producto")
public class ProductoServlet extends HttpServlet {
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
                    deleteProducto(request, response);
                    break;
                default:
                    listProductos(request, response);
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
                    insertProducto(request, response);
                    break;
                case "update":
                    updateProducto(request, response);
                    break;
                default:
                    listProductos(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void listProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("productos", productoService.obtenerTodosProductos());
        request.getRequestDispatcher("/WEB-INF/views/admin/productos/list.jsp").forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/productos/form.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Producto producto = productoService.obtenerProductoPorId(id);
        request.setAttribute("producto", producto);
        request.getRequestDispatcher("/WEB-INF/views/admin/productos/form.jsp").forward(request, response);
    }
    
    private void insertProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Producto producto = new Producto();
        producto.setCategoriaId(Integer.parseInt(request.getParameter("categoria_id")));
        producto.setNombre(request.getParameter("nombre"));
        producto.setDescripcion(request.getParameter("descripcion"));
        producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
        producto.setImagenUrl(request.getParameter("imagen_url"));
        producto.setDisponible(Boolean.parseBoolean(request.getParameter("disponible")));
        producto.setPersonalizable(Boolean.parseBoolean(request.getParameter("personalizable")));
        
        productoService.crearProducto(producto);
        response.sendRedirect("producto");
    }
    
    private void updateProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Producto producto = productoService.obtenerProductoPorId(id);
        
        producto.setCategoriaId(Integer.parseInt(request.getParameter("categoria_id")));
        producto.setNombre(request.getParameter("nombre"));
        producto.setDescripcion(request.getParameter("descripcion"));
        producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
        producto.setImagenUrl(request.getParameter("imagen_url"));
        producto.setDisponible(Boolean.parseBoolean(request.getParameter("disponible")));
        producto.setPersonalizable(Boolean.parseBoolean(request.getParameter("personalizable")));
        
        productoService.actualizarProducto(producto);
        response.sendRedirect("producto");
    }
    
    private void deleteProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productoService.eliminarProducto(id);
        response.sendRedirect("producto");
    }
}
