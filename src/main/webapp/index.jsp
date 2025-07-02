<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Cafetería</title>
    <link rel="stylesheet" href="css/empleado.css">
</head>
<body>
<div class="container">
    <h1>Cafetería Morales</h1>
    <form action="login" method="post">
        <label>Usuario:</label>
        <input type="text" name="usuario" required>
        <label>Contraseña:</label>
        <input type="password" name="clave" required>
        <button type="submit">Iniciar sesión</button>
    </form>
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
</div>
</body>
</html>
