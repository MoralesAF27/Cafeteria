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

public class Pedido {

    private int pedidoId;
    private int usuarioId;
    private int estadoId;
    private Timestamp fechaPedido;
    private double total;
    private String notas;
    private String direccionEntrega;
    private String metodoPago;

    public Pedido() {
    }

    public Pedido(int pedidoId, int usuarioId, int estadoId, Timestamp fechaPedido,
            double total, String notas, String direccionEntrega, String metodoPago) {
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
        this.estadoId = estadoId;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.notas = notas;
        this.direccionEntrega = direccionEntrega;
        this.metodoPago = metodoPago;
    }

    // Getters y Setters
    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public Timestamp getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Timestamp fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
