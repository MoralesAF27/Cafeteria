/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.service;

/**
 *
 * @author andre
 */
import com.cafeteria.dao.UsuarioDAO;
import com.cafeteria.model.Usuario;
import com.cafeteria.util.SecurityUtil;
import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public boolean registrarUsuario(Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            return false;
        }
        
        // Hashear la contraseña antes de guardarla
        String hashedPassword = SecurityUtil.hashPassword(usuario.getPassword());
        usuario.setPassword(hashedPassword);
        
        return usuarioDAO.insert(usuario);
    }
    
    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioDAO.findById(id);
    }
    
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioDAO.findAll();
    }
    
    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioDAO.update(usuario);
    }
    
    public boolean eliminarUsuario(int id) {
        return usuarioDAO.delete(id);
    }
    
    public Usuario autenticarUsuario(String email, String password) {
        Usuario usuario = usuarioDAO.findByEmail(email);
        if (usuario != null) {
            String hashedPassword = SecurityUtil.hashPassword(password);
            if (usuario.getPassword().equals(hashedPassword)) {
                return usuario;
            }
        }
        return null;
    }
    
    public boolean existeEmail(String email) {
        return usuarioDAO.findByEmail(email) != null;
    }
}
