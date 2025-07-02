/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.controller;

/**
 *
 * @author andre
 */
import com.cafeteria.model.Usuario;
import com.cafeteria.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
    private final UsuarioService usuarioService = new UsuarioService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteUsuario(request, response);
                    break;
                default:
                    listUsuarios(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            switch (action) {
                case "insert":
                    insertUsuario(request, response);
                    break;
                case "update":
                    updateUsuario(request, response);
                    break;
                case "login":
                    loginUsuario(request, response);
                    break;
                default:
                    listUsuarios(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void listUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("usuarios", usuarioService.obtenerTodosUsuarios());
        request.getRequestDispatcher("/WEB-INF/views/admin/usuarios/list.jsp").forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/usuarios/form.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/WEB-INF/views/admin/usuarios/form.jsp").forward(request, response);
    }
    
    private void insertUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = new Usuario();
        usuario.setRolId(Integer.parseInt(request.getParameter("rol_id")));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setPassword(request.getParameter("password"));
        usuario.setTelefono(request.getParameter("telefono"));
        usuario.setDireccion(request.getParameter("direccion"));
        
        usuarioService.registrarUsuario(usuario);
        response.sendRedirect("usuario");
    }
    
    private void updateUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        
        usuario.setRolId(Integer.parseInt(request.getParameter("rol_id")));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setPassword(request.getParameter("password"));
        usuario.setTelefono(request.getParameter("telefono"));
        usuario.setDireccion(request.getParameter("direccion"));
        usuario.setActivo(Boolean.parseBoolean(request.getParameter("activo")));
        
        usuarioService.actualizarUsuario(usuario);
        response.sendRedirect("usuario");
    }
    
    private void deleteUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioService.eliminarUsuario(id);
        response.sendRedirect("usuario");
    }
    
    private void loginUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Usuario usuario = usuarioService.autenticarUsuario(email, password);
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            
            switch (usuario.getRolId()) {
                case 1: // Administrador
                    response.sendRedirect("admin/dashboard");
                    break;
                case 2: // Empleado
                    response.sendRedirect("empleado/dashboard");
                    break;
                default: // Cliente
                    response.sendRedirect("cliente/dashboard");
            }
        } else {
            request.setAttribute("error", "Email o contraseña incorrectos");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        }
    }
}
