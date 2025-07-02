/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.service;

/**
 *
 * @author andre
 */
import com.cafeteria.dao.InventarioDAO;
import com.cafeteria.model.Inventario;
import java.util.List;

public class InventarioService {
    private final InventarioDAO inventarioDAO;
    
    public InventarioService() {
        this.inventarioDAO = new InventarioDAO();
    }
    
    public boolean agregarItemInventario(Inventario inventario) {
        return inventarioDAO.insert(inventario);
    }
    
    public Inventario obtenerItemPorId(int id) {
        return inventarioDAO.findById(id);
    }
    
    public Inventario obtenerItemPorProducto(int productoId) {
        return inventarioDAO.findByProductoId(productoId);
    }
    
    public List<Inventario> obtenerTodoInventario() {
        return inventarioDAO.findAll();
    }
    
    public List<Inventario> obtenerInventarioBajo() {
        return inventarioDAO.findStockBajo();
    }
    
    public boolean actualizarItemInventario(Inventario inventario) {
        return inventarioDAO.update(inventario);
    }
    
    public boolean actualizarCantidad(int productoId, int cantidad) {
        return inventarioDAO.updateCantidad(productoId, cantidad);
    }
    
    public boolean eliminarItemInventario(int id) {
        return inventarioDAO.delete(id);
    }
}
