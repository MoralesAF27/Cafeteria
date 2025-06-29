<%-- 
    Document   : historial
    Created on : 29/06/2025, 9:24:25 a. m.
    Author     : andre
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Historial de Compras</title>
    <link rel="stylesheet" href="../../css/empleado.css">
</head>
<body>
<div class="container">
    <h2>Historial de Compras</h2>

    <c:if test="${empty historial}">
        <p>No tienes compras registradas.</p>
    </c:if>

    <c:if test="${not empty historial}">
        <table>
            <tr>
                <th>Fecha</th>
                <th>Producto</th>
                <th>Cantidad</th>
                <th>Total</th>
            </tr>
            <c:forEach var="detalle" items="${historial}">
                <tr>
                    <td>${detalle.fecha}</td>
                    <td>${detalle.producto.nombre}</td>
                    <td>${detalle.cantidad}</td>
                    <td>$${detalle.total}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>

