<%@ page session="true" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio</title>
    <link rel="stylesheet" href="css/empleado.css">
</head>
<body>
<div class="container">
    <h2>Bienvenido, ${sessionScope.usuario.nombre}</h2>
    <ul>
        <li><a href="empleado/dashboard.jsp">Dashboard</a></li>
        <li><a href="empleado/pedidos/list.jsp">Pedidos</a></li>
        <li><a href="empleado/inventario/alertas.jsp">Inventario</a></li>
        <li><a href="cliente/historial.jsp">Mi Historial</a></li>
        <li><a href="logout.jsp">Cerrar Sesión</a></li>
    </ul>
</div>
</body>
</html>

