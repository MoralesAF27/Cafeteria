/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.dao;

/**
 *
 * @author andre
 */
import com.cafeteria.model.Inventario;
import com.cafeteria.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InventarioDAO {
    private static final Logger logger = LogManager.getLogger(InventarioDAO.class);
    
    private static final String INSERT_SQL = "INSERT INTO inventario(producto_id, cantidad_disponible, cantidad_minima) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM inventario WHERE item_id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM inventario";
    private static final String UPDATE_SQL = "UPDATE inventario SET cantidad_disponible=?, cantidad_minima=? WHERE item_id=?";
    private static final String DELETE_SQL = "DELETE FROM inventario WHERE item_id=?";
    private static final String SELECT_BY_PRODUCTO_SQL = "SELECT * FROM inventario WHERE producto_id = ?";
    private static final String SELECT_STOCK_BAJO_SQL = "SELECT * FROM inventario WHERE cantidad_disponible < cantidad_minima";
    private static final String UPDATE_CANTIDAD_SQL = "UPDATE inventario SET cantidad_disponible = ? WHERE producto_id = ?";
    
    public boolean insert(Inventario inventario) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            
            statement.setInt(1, inventario.getProductoId());
            statement.setInt(2, inventario.getCantidadDisponible());
            statement.setInt(3, inventario.getCantidadMinima());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error al insertar item de inventario", e);
            return false;
        }
    }
    
    public Inventario findById(int id) {
        Inventario inventario = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    inventario = mapResultSetToInventario(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Error al buscar item de inventario por ID", e);
        }
        return inventario;
    }
    
    public List<Inventario> findAll() {
        List<Inventario> inventarios = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                inventarios.add(mapResultSetToInventario(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error al obtener todos los items de inventario", e);
        }
        return inventarios;
    }
    
    public boolean update(Inventario inventario) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            
            statement.setInt(1, inventario.getCantidadDisponible());
            statement.setInt(2, inventario.getCantidadMinima());
            statement.setInt(3, inventario.getItemId());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error al actualizar item de inventario", e);
            return false;
        }
    }
    
    public boolean delete(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error al eliminar item de inventario", e);
            return false;
        }
    }
    
    public Inventario findByProductoId(int productoId) {
        Inventario inventario = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_PRODUCTO_SQL)) {
            
            statement.setInt(1, productoId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    inventario = mapResultSetToInventario(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Error al buscar inventario por producto", e);
        }
        return inventario;
    }
    
    public List<Inventario> findStockBajo() {
        List<Inventario> inventarios = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_STOCK_BAJO_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                inventarios.add(mapResultSetToInventario(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error al obtener inventario con stock bajo", e);
        }
        return inventarios;
    }
    
    public boolean updateCantidad(int productoId, int cantidad) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CANTIDAD_SQL)) {
            
            statement.setInt(1, cantidad);
            statement.setInt(2, productoId);
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error al actualizar cantidad en inventario", e);
            return false;
        }
    }
    
    private Inventario mapResultSetToInventario(ResultSet resultSet) throws SQLException {
        return new Inventario(
            resultSet.getInt("item_id"),
            resultSet.getInt("producto_id"),
            resultSet.getInt("cantidad_disponible"),
            resultSet.getInt("cantidad_minima"),
            resultSet.getTimestamp("fecha_actualizacion")
        );
    }
}
