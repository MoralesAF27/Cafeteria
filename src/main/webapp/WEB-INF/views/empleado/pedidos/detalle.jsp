<%-- 
    Document   : detalle
    Created on : 29/06/2025, 9:24:09 a. m.
    Author     : andre
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalle del Pedido</title>
    <link rel="stylesheet" href="../../../css/empleado.css">
</head>
<body>
<div class="container">
    <h2>Detalle del Pedido #${pedido.id}</h2>

    <p>Cliente: ${pedido.cliente.nombre}</p>
    <p>Fecha: ${pedido.fecha}</p>
    <p>Estado: ${pedido.estado}</p>

    <table>
        <tr>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Subtotal</th>
        </tr>
        <c:forEach var="item" items="${pedido.detalles}">
            <tr>
                <td>${item.producto.nombre}</td>
                <td>${item.cantidad}</td>
                <td>$${item.subtotal}</td>
            </tr>
        </c:forEach>
    </table>

    <h3>Total: $${pedido.total}</h3>
</div>
</body>
</html>

