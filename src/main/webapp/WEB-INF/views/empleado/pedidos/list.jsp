<%-- 
    Document   : list
    Created on : 29/06/2025, 9:22:57 a. m.
    Author     : andre
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Pedidos</title>
    <link rel="stylesheet" href="../../../css/empleado.css">
</head>
<body>
<div class="container">
    <h2>Pedidos Recientes</h2>

    <c:if test="${empty listaPedidos}">
        <p>No hay pedidos registrados.</p>
    </c:if>

    <c:if test="${not empty listaPedidos}">
        <table>
            <tr>
                <th>ID</th>
                <th>Cliente</th>
                <th>Fecha</th>
                <th>Total</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
            <c:forEach var="pedido" items="${listaPedidos}">
                <tr>
                    <td>${pedido.id}</td>
                    <td>${pedido.cliente.nombre}</td>
                    <td>${pedido.fecha}</td>
                    <td>$${pedido.total}</td>
                    <td>${pedido.estado}</td>
                    <td><a href="detalle.jsp?id=${pedido.id}">Ver</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>

