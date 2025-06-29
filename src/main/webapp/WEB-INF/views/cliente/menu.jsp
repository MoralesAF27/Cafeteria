<%-- 
    Document   : menu
    Created on : 29/06/2025, 9:23:46 a. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú - ${appName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cliente.css">
</head>
<body>
    <%@include file="../templates/header.jsp" %>
    
    <div class="container">
        <h1>Nuestro Menú</h1>
        
        <div class="productos-grid">
            <c:forEach var="producto" items="${productos}">
                <div class="producto-card">
                    <img src="${pageContext.request.contextPath}/images/productos/${producto.imagenUrl}" alt="${producto.nombre}">
                    <h3>${producto.nombre}</h3>
                    <p>${producto.descripcion}</p>
                    <span class="precio">$${producto.precio}</span>
                    
                    <form action="${pageContext.request.contextPath}/pedido" method="post">
                        <input type="hidden" name="action" value="agregar">
                        <input type="hidden" name="producto_id" value="${producto.productoId}">
                        <button type="submit" class="btn-add">Agregar al pedido</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
    
    <%@include file="../templates/footer.jsp" %>
</body>
</html>
