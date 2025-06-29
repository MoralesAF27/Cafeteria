/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.model;

/**
 *
 * @author andre
 */
public class OpcionPersonalizacion {
    private int opcionId;
    private int productoId;
    private String nombre;
    private String tipo;
    private String valores;
    private double precioExtra;

    public OpcionPersonalizacion() {}

    public OpcionPersonalizacion(int opcionId, int productoId, String nombre, 
                                String tipo, String valores, double precioExtra) {
        this.opcionId = opcionId;
        this.productoId = productoId;
        this.nombre = nombre;
        this.tipo = tipo;
        this.valores = valores;
        this.precioExtra = precioExtra;
    }

    // Getters y Setters
    public int getOpcionId() { return opcionId; }
    public void setOpcionId(int opcionId) { this.opcionId = opcionId; }
    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getValores() { return valores; }
    public void setValores(String valores) { this.valores = valores; }
    public double getPrecioExtra() { return precioExtra; }
    public void setPrecioExtra(double precioExtra) { this.precioExtra = precioExtra; }
}
