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
    <title>Gestión de Inventario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <%@include file="../../WEB-INF/templates/header.jsp" %>
    <%@include file="../../WEB-INF/templates/navigation.jsp" %>
    
    <div class="container">
        <h1>Inventario</h1>
        
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/inventario?action=new" class="btn btn-success">Agregar Item</a>
            <a href="${pageContext.request.contextPath}/inventario?action=lowStock" class="btn btn-warning ml-2">
                Ver Stock Bajo
            </a>
        </div>
        
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Producto</th>
                    <th>Disponible</th>
                    <th>Mínimo</th>
                    <th>Última Actualización</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${inventario}" var="item">
                    <tr>
                        <td>${item.item_id}</td>
                        <td>${item.producto.nombre}</td>
                        <td class="${item.cantidad_disponible < item.cantidad_minima ? 'text-danger font-weight-bold' : ''}">
                            ${item.cantidad_disponible}
                        </td>
                        <td>${item.cantidad_minima}</td>
                        <td>${item.fecha_actualizacion}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/inventario?action=edit&id=${item.item_id}" 
                               class="btn btn-sm btn-primary">Editar</a>
                            <a href="${pageContext.request.contextPath}/inventario?action=delete&id=${item.item_id}" 
                               class="btn btn-sm btn-danger" onclick="return confirm('¿Eliminar este item?')">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <%@include file="../../WEB-INF/templates/footer.jsp" %>
</body>
</html>
