/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.dao;

/**
 *
 * @author andre
 */
import com.cafeteria.model.Producto;
import com.cafeteria.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAO {
    private static final Logger logger = Logger.getLogger(ProductoDAO.class.getName());
    
    private static final String INSERT_SQL = "INSERT INTO productos(categoria_id, nombre, descripcion, precio, imagen_url, disponible, personalizable) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM productos WHERE producto_id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM productos";
    private static final String UPDATE_SQL = "UPDATE productos SET categoria_id=?, nombre=?, descripcion=?, precio=?, imagen_url=?, disponible=?, personalizable=? WHERE producto_id=?";
    private static final String DELETE_SQL = "DELETE FROM productos WHERE producto_id=?";
    private static final String SELECT_BY_CATEGORIA_SQL = "SELECT * FROM productos WHERE categoria_id = ?";
    
    public boolean insert(Producto producto) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            
            statement.setInt(1, producto.getCategoriaId());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getDescripcion());
            statement.setDouble(4, producto.getPrecio());
            statement.setString(5, producto.getImagenUrl());
            statement.setBoolean(6, producto.isDisponible());
            statement.setBoolean(7, producto.isPersonalizable());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar producto", e);
            return false;
        }
    }
    
    public Producto findById(int id) {
        Producto producto = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    producto = mapResultSetToProducto(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar producto por ID", e);
        }
        return producto;
    }
    
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                productos.add(mapResultSetToProducto(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener todos los productos", e);
        }
        return productos;
    }
    
    public boolean update(Producto producto) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            
            statement.setInt(1, producto.getCategoriaId());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getDescripcion());
            statement.setDouble(4, producto.getPrecio());
            statement.setString(5, producto.getImagenUrl());
            statement.setBoolean(6, producto.isDisponible());
            statement.setBoolean(7, producto.isPersonalizable());
            statement.setInt(8, producto.getProductoId());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar producto", e);
            return false;
        }
    }
    
    public boolean delete(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar producto", e);
            return false;
        }
    }
    
    public List<Producto> findByCategoria(int categoriaId) {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_CATEGORIA_SQL)) {
            
            statement.setInt(1, categoriaId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    productos.add(mapResultSetToProducto(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar productos por categoría", e);
        }
        return productos;
    }
    
    private Producto mapResultSetToProducto(ResultSet resultSet) throws SQLException {
        return new Producto(
            resultSet.getInt("producto_id"),
            resultSet.getInt("categoria_id"),
            resultSet.getString("nombre"),
            resultSet.getString("descripcion"),
            resultSet.getDouble("precio"),
            resultSet.getString("imagen_url"),
            resultSet.getBoolean("disponible"),
            resultSet.getBoolean("personalizable")
        );
    }
}