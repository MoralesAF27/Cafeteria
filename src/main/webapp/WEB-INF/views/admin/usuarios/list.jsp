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
    <title>Gestión de Usuarios</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <%@include file="../../WEB-INF/templates/header.jsp" %>
    <%@include file="../../WEB-INF/templates/navigation.jsp" %>
    
    <div class="container">
        <h1>Usuarios</h1>
        
        <a href="${pageContext.request.contextPath}/usuario?action=new" class="btn btn-success mb-3">Agregar Usuario</a>
        
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Rol</th>
                    <th>Teléfono</th>
                    <th>Activo</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${usuarios}" var="usuario">
                    <tr>
                        <td>${usuario.usuario_id}</td>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.email}</td>
                        <td>${usuario.rol.nombre}</td>
                        <td>${usuario.telefono}</td>
                        <td>${usuario.activo ? 'Sí' : 'No'}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/usuario?action=edit&id=${usuario.usuario_id}" 
                               class="btn btn-sm btn-primary">Editar</a>
                            <a href="${pageContext.request.contextPath}/usuario?action=delete&id=${usuario.usuario_id}" 
                               class="btn btn-sm btn-danger" onclick="return confirm('¿Eliminar este usuario?')">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <%@include file="../../WEB-INF/templates/footer.jsp" %>
</body>
</html>
