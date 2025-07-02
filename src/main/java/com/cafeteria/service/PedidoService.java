/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.service;

/**
 *
 * @author andre
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.cafeteria.dao.DetallePedidoDAO;
import com.cafeteria.dao.InventarioDAO;
import com.cafeteria.dao.PedidoDAO;
import com.cafeteria.model.DetallePedido;
import com.cafeteria.model.Pedido;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoService {
    private static final Logger logger = Logger.getLogger(PedidoService.class.getName());
    
    private final PedidoDAO pedidoDAO;
    private final DetallePedidoDAO detallePedidoDAO;
    private final InventarioDAO inventarioDAO;
    
    public PedidoService() {
        this.pedidoDAO = new PedidoDAO();
        this.detallePedidoDAO = new DetallePedidoDAO();
        this.inventarioDAO = new InventarioDAO();
    }
    
    public int crearPedido(Pedido pedido, List<DetallePedido> detalles) {
        try {
            int pedidoId = pedidoDAO.insert(pedido);
            
            if (pedidoId > 0) {
                for (DetallePedido detalle : detalles) {
                    detalle.setPedidoId(pedidoId);
                    if (!detallePedidoDAO.insert(detalle)) {
                        throw new RuntimeException("Error al insertar detalle de pedido");
                    }
                    
                    if (!inventarioDAO.updateCantidad(detalle.getProductoId(), -detalle.getCantidad())) {
                        throw new RuntimeException("Error al actualizar inventario");
                    }
                }
                return pedidoId;
            }
            return -1;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al crear pedido completo", e);
            return -1;
        }
    }
    
    public Pedido obtenerPedidoPorId(int id) {
        try {
            return pedidoDAO.findById(id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener pedido por ID", e);
            return null;
        }
    }
    
    public List<DetallePedido> obtenerDetallesPedido(int pedidoId) {
        try {
            return detallePedidoDAO.findByPedidoId(pedidoId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener detalles del pedido", e);
            return List.of();
        }
    }
    
    public List<Pedido> obtenerTodosPedidos() {
        try {
            return pedidoDAO.findAll();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener todos los pedidos", e);
            return List.of();
        }
    }
    
    public List<Pedido> obtenerPedidosPorUsuario(int usuarioId) {
        try {
            return pedidoDAO.findByUsuarioId(usuarioId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener pedidos por usuario", e);
            return List.of();
        }
    }
    
    public boolean actualizarPedido(Pedido pedido) {
        try {
            return pedidoDAO.update(pedido);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar pedido", e);
            return false;
        }
    }
    
    public boolean actualizarEstadoPedido(int pedidoId, int estadoId) {
        try {
            return pedidoDAO.updateEstado(pedidoId, estadoId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar estado del pedido", e);
            return false;
        }
    }
    
    public boolean eliminarPedido(int id) {
        try {
            detallePedidoDAO.deleteByPedidoId(id);
            return pedidoDAO.delete(id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar pedido", e);
            return false;
        }
    }
}