<%-- 
    Document   : dashboard
    Created on : 29/06/2025, 9:23:41 a. m.
    Author     : andre
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="../css/empleado.css">
</head>
<body>
<div class="container">
    <h2>Panel de Control</h2>
    <p>Pedidos del día: ${pedidosHoy}</p>
    <p>Total ventas hoy: $${ventasHoy}</p>
    <p>Productos con bajo stock: ${bajoStock}</p>
</div>
</body>
</html>

