/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.dao;

/**
 *
 * @author andre
 */
import com.cafeteria.model.Usuario;
import com.cafeteria.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());
    
    private static final String INSERT_SQL = "INSERT INTO usuarios(rol_id, nombre, email, password, telefono, direccion) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM usuarios WHERE usuario_id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM usuarios";
    private static final String UPDATE_SQL = "UPDATE usuarios SET rol_id=?, nombre=?, email=?, password=?, telefono=?, direccion=?, activo=? WHERE usuario_id=?";
    private static final String DELETE_SQL = "DELETE FROM usuarios WHERE usuario_id=?";
    private static final String SELECT_BY_EMAIL_SQL = "SELECT * FROM usuarios WHERE email = ?";
    
    public boolean insert(Usuario usuario) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            
            statement.setInt(1, usuario.getRolId());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getPassword());
            statement.setString(5, usuario.getTelefono());
            statement.setString(6, usuario.getDireccion());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar usuario", e);
            return false;
        }
    }
    
    public Usuario findById(int id) {
        Usuario usuario = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = mapResultSetToUsuario(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar usuario por ID", e);
        }
        return usuario;
    }
    
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                usuarios.add(mapResultSetToUsuario(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener todos los usuarios", e);
        }
        return usuarios;
    }
    
    public boolean update(Usuario usuario) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            
            statement.setInt(1, usuario.getRolId());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getPassword());
            statement.setString(5, usuario.getTelefono());
            statement.setString(6, usuario.getDireccion());
            statement.setBoolean(7, usuario.isActivo());
            statement.setInt(8, usuario.getUsuarioId());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar usuario", e);
            return false;
        }
    }
    
    public boolean delete(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar usuario", e);
            return false;
        }
    }
    
    public Usuario findByEmail(String email) {
        Usuario usuario = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_SQL)) {
            
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = mapResultSetToUsuario(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar usuario por email", e);
        }
        return usuario;
    }
    
    private Usuario mapResultSetToUsuario(ResultSet resultSet) throws SQLException {
        return new Usuario(
            resultSet.getInt("usuario_id"),
            resultSet.getInt("rol_id"),
            resultSet.getString("nombre"),
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getString("telefono"),
            resultSet.getString("direccion"),
            resultSet.getTimestamp("fecha_registro"),
            resultSet.getBoolean("activo")
        );
    }
}