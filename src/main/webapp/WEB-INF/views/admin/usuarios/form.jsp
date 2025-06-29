<%-- 
    Document   : form
    Created on : 29/06/2025, 9:23:03 a. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${empty usuario.usuario_id ? 'Agregar' : 'Editar'} Usuario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <%@include file="../../WEB-INF/templates/header.jsp" %>
    <%@include file="../../WEB-INF/templates/navigation.jsp" %>
    
    <div class="container">
        <h1>${empty usuario.usuario_id ? 'Agregar' : 'Editar'} Usuario</h1>
        
        <form action="${pageContext.request.contextPath}/usuario" method="post">
            <input type="hidden" name="action" value="${empty usuario.usuario_id ? 'insert' : 'update'}">
            <c:if test="${not empty usuario.usuario_id}">
                <input type="hidden" name="id" value="${usuario.usuario_id}">
            </c:if>
            
            <div class="form-group">
                <label>Rol:</label>
                <select name="rol_id" class="form-control" required>
                    <option value="">Seleccione un rol</option>
                    <c:forEach items="${roles}" var="rol">
                        <option value="${rol.rol_id}" ${usuario.rol_id == rol.rol_id ? 'selected' : ''}>
                            ${rol.nombre}
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label>Nombre:</label>
                <input type="text" name="nombre" value="${usuario.nombre}" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="${usuario.email}" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Contraseña:</label>
                <input type="password" name="password" class="form-control" ${empty usuario.usuario_id ? 'required' : ''}>
                <small class="text-muted">${not empty usuario.usuario_id ? 'Dejar en blanco para mantener la contraseña actual' : ''}</small>
            </div>
            
            <div class="form-group">
                <label>Teléfono:</label>
                <input type="tel" name="telefono" value="${usuario.telefono}" class="form-control">
            </div>
            
            <div class="form-group">
                <label>Dirección:</label>
                <textarea name="direccion" class="form-control">${usuario.direccion}</textarea>
            </div>
            
            <c:if test="${not empty usuario.usuario_id}">
                <div class="form-check mb-3">
                    <input type="checkbox" name="activo" class="form-check-input" id="activo" 
                           ${usuario.activo ? 'checked' : ''}>
                    <label class="form-check-label" for="activo">Activo</label>
                </div>
            </c:if>
            
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="${pageContext.request.contextPath}/usuario" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
    
    <%@include file="../../WEB-INF/templates/footer.jsp" %>
</body>
</html>
