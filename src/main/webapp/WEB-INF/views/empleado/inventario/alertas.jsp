<%-- 
    Document   : alertas
    Created on : 29/06/2025, 9:25:13 a. m.
    Author     : andre
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Alertas de Inventario</title>
    <link rel="stylesheet" href="../../../css/empleado.css">
</head>
<body>
<div class="container">
    <h2>Productos con Bajo Inventario</h2>

    <c:if test="${empty productosBajos}">
        <p>Todo el inventario está en niveles óptimos.</p>
    </c:if>

    <c:if test="${not empty productosBajos}">
        <table>
            <tr>
                <th>Nombre</th>
                <th>Stock Actual</th>
                <th>Stock Mínimo</th>
            </tr>
            <c:forEach var="p" items="${productosBajos}">
                <tr>
                    <td>${p.nombre}</td>
                    <td>${p.stock}</td>
                    <td>${p.stockMinimo}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>

