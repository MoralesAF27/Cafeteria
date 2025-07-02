/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.model;

/**
 *
 * @author andre
 */
public class DetallePedido {

    private int detalleId;
    private int pedidoId;
    private int productoId;
    private int cantidad;
    private double precioUnitario;
    private String personalizaciones;

    public DetallePedido() {
    }

    public DetallePedido(int detalleId, int pedidoId, int productoId, int cantidad,
            double precioUnitario, String personalizaciones) {
        this.detalleId = detalleId;
        this.pedidoId = pedidoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.personalizaciones = personalizaciones;
    }

    // Getters y Setters
    public int getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(int detalleId) {
        this.detalleId = detalleId;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getPersonalizaciones() {
        return personalizaciones;
    }

    public void setPersonalizaciones(String personalizaciones) {
        this.personalizaciones = personalizaciones;
    }
}
