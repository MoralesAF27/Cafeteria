/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.dao;

/**
 *
 * @author andre
 */
import com.cafeteria.model.Pedido;
import com.cafeteria.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDAO {
    private static final Logger logger = Logger.getLogger(PedidoDAO.class.getName());
    
    private static final String INSERT_SQL = "INSERT INTO pedidos(usuario_id, estado_id, total, notas, direccion_entrega, metodo_pago) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM pedidos WHERE pedido_id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM pedidos";
    private static final String UPDATE_SQL = "UPDATE pedidos SET estado_id=?, notas=?, direccion_entrega=?, metodo_pago=? WHERE pedido_id=?";
    private static final String DELETE_SQL = "DELETE FROM pedidos WHERE pedido_id=?";
    private static final String SELECT_BY_USUARIO_SQL = "SELECT * FROM pedidos WHERE usuario_id = ?";
    private static final String UPDATE_ESTADO_SQL = "UPDATE pedidos SET estado_id = ? WHERE pedido_id = ?";
    
    public int insert(Pedido pedido) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, pedido.getUsuarioId());
            statement.setInt(2, pedido.getEstadoId());
            statement.setDouble(3, pedido.getTotal());
            statement.setString(4, pedido.getNotas());
            statement.setString(5, pedido.getDireccionEntrega());
            statement.setString(6, pedido.getMetodoPago());
            
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating pedido failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating pedido failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar pedido", e);
            return -1;
        }
    }
    
    public Pedido findById(int id) {
        Pedido pedido = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    pedido = mapResultSetToPedido(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar pedido por ID", e);
        }
        return pedido;
    }
    
    public List<Pedido> findAll() {
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                pedidos.add(mapResultSetToPedido(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener todos los pedidos", e);
        }
        return pedidos;
    }
    
    public boolean update(Pedido pedido) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            
            statement.setInt(1, pedido.getEstadoId());
            statement.setString(2, pedido.getNotas());
            statement.setString(3, pedido.getDireccionEntrega());
            statement.setString(4, pedido.getMetodoPago());
            statement.setInt(5, pedido.getPedidoId());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar pedido", e);
            return false;
        }
    }
    
    public boolean delete(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar pedido", e);
            return false;
        }
    }
    
    public List<Pedido> findByUsuarioId(int usuarioId) {
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_USUARIO_SQL)) {
            
            statement.setInt(1, usuarioId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    pedidos.add(mapResultSetToPedido(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar pedidos por usuario", e);
        }
        return pedidos;
    }
    
    public boolean updateEstado(int pedidoId, int estadoId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ESTADO_SQL)) {
            
            statement.setInt(1, estadoId);
            statement.setInt(2, pedidoId);
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar estado del pedido", e);
            return false;
        }
    }
    
    private Pedido mapResultSetToPedido(ResultSet resultSet) throws SQLException {
        return new Pedido(
            resultSet.getInt("pedido_id"),
            resultSet.getInt("usuario_id"),
            resultSet.getInt("estado_id"),
            resultSet.getTimestamp("fecha_pedido"),
            resultSet.getDouble("total"),
            resultSet.getString("notas"),
            resultSet.getString("direccion_entrega"),
            resultSet.getString("metodo_pago")
        );
    }
}