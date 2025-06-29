/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafeteria.service;

/**
 *
 * @author andre
 */
import com.cafeteria.dao.ReporteDAO;
import java.util.List;
import java.util.Map;

public class ReporteService {
    private final ReporteDAO reporteDAO;
    
    public ReporteService() {
        this.reporteDAO = new ReporteDAO();
    }
    
    public List<Map<String, Object>> obtenerVentasPorPeriodo(String fechaInicio, String fechaFin) {
        return reporteDAO.getVentasPorPeriodo(fechaInicio, fechaFin);
    }
    
    public List<Map<String, Object>> obtenerProductosMasVendidos(String fechaInicio, String fechaFin) {
        return reporteDAO.getProductosMasVendidos(fechaInicio, fechaFin);
    }
    
    public List<Map<String, Object>> obtenerInventarioBajo() {
        return reporteDAO.getInventarioBajo();
    }
}
