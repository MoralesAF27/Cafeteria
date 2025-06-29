/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.dao;

/**
 *
 * @author andre
 */
import com.cafeteria.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReporteDAO {
    private static final Logger logger = LogManager.getLogger(ReporteDAO.class);
    
    private static final String VENTAS_POR_PERIODO_SQL = 
        "SELECT DATE(fecha_pedido) as fecha, SUM(total) as total " +
        "FROM pedidos " +
        "WHERE fecha_pedido BETWEEN ? AND ? " +
        "GROUP BY DATE(fecha_pedido) " +
        "ORDER BY fecha";
    
    private static final String PRODUCTOS_MAS_VENDIDOS_SQL = 
        "SELECT p.nombre, SUM(dp.cantidad) as cantidad_vendida, SUM(dp.cantidad * dp.precio_unitario) as total " +
        "FROM detalles_pedido dp " +
        "JOIN productos p ON dp.producto_id = p.producto_id " +
        "JOIN pedidos pe ON dp.pedido_id = pe.pedido_id " +
        "WHERE pe.fecha_pedido BETWEEN ? AND ? " +
        "GROUP BY p.nombre " +
        "ORDER BY cantidad_vendida DESC " +
        "LIMIT 10";
    
    private static final String INVENTARIO_BAJO_SQL = 
        "SELECT p.nombre, i.cantidad_disponible, i.cantidad_minima " +
        "FROM inventario i " +
        "JOIN productos p ON i.producto_id = p.producto_id " +
        "WHERE i.cantidad_disponible < i.cantidad_minima";
    
    public List<Map<String, Object>> getVentasPorPeriodo(String fechaInicio, String fechaFin) {
        List<Map<String, Object>> ventas = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(VENTAS_POR_PERIODO_SQL)) {
            
            statement.setString(1, fechaInicio);
            statement.setString(2, fechaFin);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> venta = new HashMap<>();
                    venta.put("fecha", resultSet.getDate("fecha"));
                    venta.put("total", resultSet.getDouble("total"));
                    ventas.add(venta);
                }
            }
        } catch (SQLException e) {
            logger.error("Error al obtener ventas por periodo", e);
        }
        
        return ventas;
    }
    
    public List<Map<String, Object>> getProductosMasVendidos(String fechaInicio, String fechaFin) {
        List<Map<String, Object>> productos = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(PRODUCTOS_MAS_VENDIDOS_SQL)) {
            
            statement.setString(1, fechaInicio);
            statement.setString(2, fechaFin);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> producto = new HashMap<>();
                    producto.put("nombre", resultSet.getString("nombre"));
                    producto.put("cantidad_vendida", resultSet.getInt("cantidad_vendida"));
                    producto.put("total", resultSet.getDouble("total"));
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            logger.error("Error al obtener productos más vendidos", e);
        }
        
        return productos;
    }
    
    public List<Map<String, Object>> getInventarioBajo() {
        List<Map<String, Object>> inventarioBajo = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INVENTARIO_BAJO_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("nombre", resultSet.getString("nombre"));
                item.put("cantidad_disponible", resultSet.getInt("cantidad_disponible"));
                item.put("cantidad_minima", resultSet.getInt("cantidad_minima"));
                inventarioBajo.add(item);
            }
        } catch (SQLException e) {
            logger.error("Error al obtener inventario bajo", e);
        }
        
        return inventarioBajo;
    }
}
