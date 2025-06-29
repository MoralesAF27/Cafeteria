<%-- 
    Document   : ventas
    Created on : 29/06/2025, 9:23:18 a. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte de Ventas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <%@include file="../../WEB-INF/templates/header.jsp" %>
    <%@include file="../../WEB-INF/templates/navigation.jsp" %>
    
    <div class="container">
        <h1>Reporte de Ventas</h1>
        
        <form method="get" class="mb-4">
            <div class="form-row">
                <div class="col-md-3">
                    <label>Fecha Inicio:</label>
                    <input type="date" name="fecha_inicio" value="${fechaInicio}" class="form-control">
                </div>
                <div class="col-md-3">
                    <label>Fecha Fin:</label>
                    <input type="date" name="fecha_fin" value="${fechaFin}" class="form-control">
                </div>
                <div class="col-md-2 align-self-end">
                    <button type="submit" class="btn btn-primary">Filtrar</button>
                </div>
            </div>
        </form>
        
        <div class="row">
            <div class="col-md-6">
                <h3>Ventas por Período</h3>
                <table class="table table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th>Fecha</th>
                            <th>Total Ventas</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ventas}" var="venta">
                            <tr>
                                <td><fmt:formatDate value="${venta.fecha}" pattern="dd/MM/yyyy"/></td>
                                <td><fmt:formatNumber value="${venta.total}" type="currency"/></td>
                            </tr>
                        </c:forEach>
                        <c:if test="${not empty ventas}">
                            <tr class="table-success">
                                <td><strong>Total</strong></td>
                                <td>
                                    <strong>
                                        <fmt:formatNumber value="${ventas.stream().map(v -> v.total).sum()}" type="currency"/>
                                    </strong>
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
            
            <div class="col-md-6">
                <h3>Productos Más Vendidos</h3>
                <table class="table table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th>Producto</th>
                            <th>Cantidad</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${productosMasVendidos}" var="producto">
                            <tr>
                                <td>${producto.nombre}</td>
                                <td>${producto.cantidad_vendida}</td>
                                <td><fmt:formatNumber value="${producto.total}" type="currency"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
        <div class="row mt-4">
            <div class="col-md-12">
                <canvas id="ventasChart" height="100"></canvas>
            </div>
        </div>
    </div>
    
    <script>
        const ctx = document.getElementById('ventasChart').getContext('2d');
        const chart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: [
                    <c:forEach items="${ventas}" var="venta">
                        '<fmt:formatDate value="${venta.fecha}" pattern="dd/MM"/>',
                    </c:forEach>
                ],
                datasets: [{
                    label: 'Ventas Diarias',
                    data: [
                        <c:forEach items="${ventas}" var="venta">
                            ${venta.total},
                        </c:forEach>
                    ],
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 2,
                    tension: 0.1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
    
    <%@include file="../../WEB-INF/templates/footer.jsp" %>
</body>
</html>
