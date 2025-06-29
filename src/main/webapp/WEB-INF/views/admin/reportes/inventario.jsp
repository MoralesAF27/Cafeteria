<%-- 
    Document   : inventario
    Created on : 29/06/2025, 9:23:28 a. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte de Inventario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <%@include file="../../WEB-INF/templates/header.jsp" %>
    <%@include file="../../WEB-INF/templates/navigation.jsp" %>
    
    <div class="container">
        <h1>Reporte de Inventario</h1>
        
        <div class="row">
            <div class="col-md-6">
                <h3>Productos con Stock Bajo</h3>
                <table class="table table-bordered">
                    <thead class="thead-light">
                        <tr>
                            <th>Producto</th>
                            <th>Disponible</th>
                            <th>Mínimo</th>
                            <th>Diferencia</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${inventarioBajo}" var="item">
                            <tr class="table-danger">
                                <td>${item.nombre}</td>
                                <td>${item.cantidad_disponible}</td>
                                <td>${item.cantidad_minima}</td>
                                <td>${item.cantidad_disponible - item.cantidad_minima}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <div class="col-md-6">
                <canvas id="inventarioChart" width="400" height="400"></canvas>
            </div>
        </div>
    </div>
    
    <script>
        const ctx = document.getElementById('inventarioChart').getContext('2d');
        const chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: [
                    <c:forEach items="${inventarioBajo}" var="item">
                        '${item.nombre}',
                    </c:forEach>
                ],
                datasets: [{
                    label: 'Stock Disponible',
                    data: [
                        <c:forEach items="${inventarioBajo}" var="item">
                            ${item.cantidad_disponible},
                        </c:forEach>
                    ],
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }, {
                    label: 'Stock Mínimo',
                    data: [
                        <c:forEach items="${inventarioBajo}" var="item">
                            ${item.cantidad_minima},
                        </c:forEach>
                    ],
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
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
