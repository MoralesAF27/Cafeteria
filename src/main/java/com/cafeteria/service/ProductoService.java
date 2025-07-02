/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.service;

/**
 *
 * @author andre
 */
import com.cafeteria.dao.ProductoDAO;
import com.cafeteria.model.Producto;
import java.util.List;

public class ProductoService {
    private final ProductoDAO productoDAO;
    
    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }
    
    public boolean crearProducto(Producto producto) {
        return productoDAO.insert(producto);
    }
    
    public Producto obtenerProductoPorId(int id) {
        return productoDAO.findById(id);
    }
    
    public List<Producto> obtenerTodosProductos() {
        return productoDAO.findAll();
    }
    
    public List<Producto> obtenerProductosPorCategoria(int categoriaId) {
        return productoDAO.findByCategoria(categoriaId);
    }
    
    public boolean actualizarProducto(Producto producto) {
        return productoDAO.update(producto);
    }
    
    public boolean eliminarProducto(int id) {
        return productoDAO.delete(id);
    }
    
    public List<Producto> obtenerProductosDisponibles() {
        List<Producto> productos = productoDAO.findAll();
        productos.removeIf(p -> !p.isDisponible());
        return productos;
    }
}
