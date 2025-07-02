/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.dao;

/**
 *
 * @author estudiantes
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.cafeteria.model.DetallePedido;
import com.cafeteria.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetallePedidoDAO {
    private static final Logger logger = Logger.getLogger(DetallePedidoDAO.class.getName());
    
    private static final String INSERT_SQL = "INSERT INTO detalles_pedido(pedido_id, producto_id, cantidad, precio_unitario, personalizaciones) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM detalles_pedido WHERE detalle_id = ?";
    private static final String SELECT_BY_PEDIDO_SQL = "SELECT * FROM detalles_pedido WHERE pedido_id = ?";
    private static final String UPDATE_SQL = "UPDATE detalles_pedido SET cantidad=?, precio_unitario=?, personalizaciones=? WHERE detalle_id=?";
    private static final String DELETE_SQL = "DELETE FROM detalles_pedido WHERE detalle_id=?";
    private static final String DELETE_BY_PEDIDO_SQL = "DELETE FROM detalles_pedido WHERE pedido_id=?";
    
    public boolean insert(DetallePedido detalle) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, detalle.getPedidoId());
            statement.setInt(2, detalle.getProductoId());
            statement.setInt(3, detalle.getCantidad());
            statement.setDouble(4, detalle.getPrecioUnitario());
            statement.setString(5, detalle.getPersonalizaciones());
            
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        detalle.setDetalleId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar detalle de pedido", e);
            return false;
        }
    }
    
    public DetallePedido findById(int id) {
        DetallePedido detalle = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    detalle = mapResultSetToDetallePedido(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar detalle de pedido por ID", e);
        }
        return detalle;
    }
    
    public List<DetallePedido> findByPedidoId(int pedidoId) {
        List<DetallePedido> detalles = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_PEDIDO_SQL)) {
            
            statement.setInt(1, pedidoId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    detalles.add(mapResultSetToDetallePedido(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar detalles por ID de pedido", e);
        }
        return detalles;
    }
    
    public boolean update(DetallePedido detalle) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            
            statement.setInt(1, detalle.getCantidad());
            statement.setDouble(2, detalle.getPrecioUnitario());
            statement.setString(3, detalle.getPersonalizaciones());
            statement.setInt(4, detalle.getDetalleId());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar detalle de pedido", e);
            return false;
        }
    }
    
    public boolean delete(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar detalle de pedido", e);
            return false;
        }
    }
    
    public boolean deleteByPedidoId(int pedidoId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_PEDIDO_SQL)) {
            
            statement.setInt(1, pedidoId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar detalles por ID de pedido", e);
            return false;
        }
    }
    
    private DetallePedido mapResultSetToDetallePedido(ResultSet resultSet) throws SQLException {
        return new DetallePedido(
            resultSet.getInt("detalle_id"),
            resultSet.getInt("pedido_id"),
            resultSet.getInt("producto_id"),
            resultSet.getInt("cantidad"),
            resultSet.getDouble("precio_unitario"),
            resultSet.getString("personalizaciones")
        );
    }
}