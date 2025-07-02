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

public class Inventario {
    private int itemId;
    private int productoId;
    private int cantidadDisponible;
    private int cantidadMinima;
    private Timestamp fechaActualizacion;

    public Inventario() {}

    public Inventario(int itemId, int productoId, int cantidadDisponible, 
                     int cantidadMinima, Timestamp fechaActualizacion) {
        this.itemId = itemId;
        this.productoId = productoId;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadMinima = cantidadMinima;
        this.fechaActualizacion = fechaActualizacion;
    }

    // Getters y Setters
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }
    public int getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(int cantidadDisponible) { this.cantidadDisponible = cantidadDisponible; }
    public int getCantidadMinima() { return cantidadMinima; }
    public void setCantidadMinima(int cantidadMinima) { this.cantidadMinima = cantidadMinima; }
    public Timestamp getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(Timestamp fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}
