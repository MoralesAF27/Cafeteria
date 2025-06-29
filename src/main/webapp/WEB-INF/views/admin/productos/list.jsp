<%-- 
    Document   : list
    Created on : 29/06/2025, 9:22:57 a. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Productos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <%@include file="../../WEB-INF/templates/header.jsp" %>
    <%@include file="../../WEB-INF/templates/navigation.jsp" %>
    
    <div class="container">
        <h1>Productos</h1>
        
        <a href="${pageContext.request.contextPath}/producto?action=new" class="btn btn-success mb-3">Agregar Producto</a>
        
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Imagen</th>
                    <th>Nombre</th>
                    <th>Categoría</th>
                    <th>Precio</th>
                    <th>Disponible</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${productos}" var="producto">
                    <tr>
                        <td>${producto.producto_id}</td>
                        <td>
                            <c:if test="${not empty producto.imagenUrl}">
                                <img src="${pageContext.request.contextPath}/images/productos/${producto.imagenUrl}" 
                                     alt="${producto.nombre}" width="50">
                            </c:if>
                        </td>
                        <td>${producto.nombre}</td>
                        <td>${producto.categoria.nombre}</td>
                        <td>$${producto.precio}</td>
                        <td>${producto.disponible ? 'Sí' : 'No'}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/producto?action=edit&id=${producto.producto_id}" 
                               class="btn btn-sm btn-primary">Editar</a>
                            <a href="${pageContext.request.contextPath}/producto?action=delete&id=${producto.producto_id}" 
                               class="btn btn-sm btn-danger" onclick="return confirm('¿Eliminar este producto?')">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <%@include file="../../WEB-INF/templates/footer.jsp" %>
</body>
</html>
