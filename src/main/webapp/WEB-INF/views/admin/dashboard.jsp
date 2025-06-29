<%-- 
    Document   : dashboard
    Created on : 29/06/2025, 9:21:58 a. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración - ${appName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <%@include file="../templates/header.jsp" %>
    <%@include file="../templates/navigation.jsp" %>
    
    <div class="container">
        <h1>Panel de Administración</h1>
        
        <div class="dashboard-cards">
            <div class="card">
                <h3>Ventas Hoy</h3>
                <p>$${ventasHoy}</p>
            </div>
            <div class="card">
                <h3>Pedidos Pendientes</h3>
                <p>${pedidosPendientes}</p>
            </div>
            <div class="card">
                <h3>Productos con Stock Bajo</h3>
                <p>${inventarioBajo}</p>
            </div>
        </div>
        
        <div class="quick-actions">
            <a href="${pageContext.request.contextPath}/producto?action=new" class="btn btn-primary">Agregar Producto</a>
            <a href="${pageContext.request.contextPath}/inventario?action=lowStock" class="btn btn-warning">Ver Stock Bajo</a>
            <a href="${pageContext.request.contextPath}/reporte?action=sales" class="btn btn-info">Ver Reportes</a>
        </div>
    </div>
    
    <%@include file="../templates/footer.jsp" %>
</body>
</html>
