/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.model;

/**
 *
 * @author andre
 */
public class Rol {
    private int rolId;
    private String nombre;
    private String descripcion;

    public Rol() {}

    public Rol(int rolId, String nombre, String descripcion) {
        this.rolId = rolId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getRolId() { return rolId; }
    public void setRolId(int rolId) { this.rolId = rolId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
