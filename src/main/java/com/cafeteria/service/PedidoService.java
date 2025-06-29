/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.service;

/**
 *
 * @author andre
 */
import com.cafeteria.dao.DetallePedidoDAO;
import com.cafeteria.dao.InventarioDAO;
import com.cafeteria.dao.PedidoDAO;
import com.cafeteria.model.DetallePedido;
import com.cafeteria.model.Pedido;
import java.util.List;

public class PedidoService {
    private final PedidoDAO pedidoDAO;
    private final DetallePedidoDAO detallePedidoDAO;
    private final InventarioDAO inventarioDAO;
    
    public PedidoService() {
        this.pedidoDAO = new PedidoDAO();
        this.detallePedidoDAO = new DetallePedidoDAO();
        this.inventarioDAO = new InventarioDAO();
    }
    
    public int crearPedido(Pedido pedido, List<DetallePedido> detalles) {
        int pedidoId = pedidoDAO.insert(pedido);
        
        if (pedidoId > 0) {
            for (DetallePedido detalle : detalles) {
                detalle.setPedidoId(pedidoId);
                detallePedidoDAO.insert(detalle);
                
                // Actualizar inventario
                inventarioDAO.updateCantidad(
                    detalle.getProductoId(), 
                    -detalle.getCantidad()
                );
            }
        }
        
        return pedidoId;
    }
    
    public Pedido obtenerPedidoPorId(int id) {
        Pedido pedido = pedidoDAO.findById(id);
        if (pedido != null) {
            pedido.setDetalles(detallePedidoDAO.findByPedidoId(id));
        }
        return pedido;
    }
    
    public List<Pedido> obtenerTodosPedidos() {
        List<Pedido> pedidos = pedidoDAO.findAll();
        for (Pedido pedido : pedidos) {
            pedido.setDetalles(detallePedidoDAO.findByPedidoId(pedido.getPedidoId()));
        }
        return pedidos;
    }
    
    public List<Pedido> obtenerPedidosPorUsuario(int usuarioId) {
        List<Pedido> pedidos = pedidoDAO.findByUsuarioId(usuarioId);
        for (Pedido pedido : pedidos) {
            pedido.setDetalles(detallePedidoDAO.findByPedidoId(pedido.getPedidoId()));
        }
        return pedidos;
    }
    
    public boolean actualizarPedido(Pedido pedido) {
        return pedidoDAO.update(pedido);
    }
    
    public boolean actualizarEstadoPedido(int pedidoId, int estadoId) {
        return pedidoDAO.updateEstado(pedidoId, estadoId);
    }
    
    public boolean eliminarPedido(int id) {
        return pedidoDAO.delete(id);
    }
}
