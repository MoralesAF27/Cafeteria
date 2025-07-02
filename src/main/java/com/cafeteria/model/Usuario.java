/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.model;

/**
 *
 * @author andre
 */
import java.sql.Timestamp;

public class Usuario {
    private int usuarioId;
    private int rolId;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String direccion;
    private Timestamp fechaRegistro;
    private boolean activo;

    public Usuario() {}

    public Usuario(int usuarioId, int rolId, String nombre, String email, String password, 
                  String telefono, String direccion, Timestamp fechaRegistro, boolean activo) {
        this.usuarioId = usuarioId;
        this.rolId = rolId;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }

    // Getters y Setters
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public int getRolId() { return rolId; }
    public void setRolId(int rolId) { this.rolId = rolId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
