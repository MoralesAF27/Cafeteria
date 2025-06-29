%<-- 
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
    <title>${empty inventario.item_id ? 'Agregar' : 'Editar'} Inventario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <%@include file="../../WEB-INF/templates/header.jsp" %>
    <%@include file="../../WEB-INF/templates/navigation.jsp" %>
    
    <div class="container">
        <h1>${empty inventario.item_id ? 'Agregar' : 'Editar'} Item de Inventario</h1>
        
        <form action="${pageContext.request.contextPath}/inventario" method="post">
            <input type="hidden" name="action" value="${empty inventario.item_id ? 'insert' : 'update'}">
            <c:if test="${not empty inventario.item_id}">
                <input type="hidden" name="id" value="${inventario.item_id}">
            </c:if>
            
            <div class="form-group">
                <label>Producto:</label>
                <select name="producto_id" class="form-control" required>
                    <option value="">Seleccione un producto</option>
                    <c:forEach items="${productos}" var="producto">
                        <option value="${producto.producto_id}" ${inventario.producto_id == producto.producto_id ? 'selected' : ''}>
                            ${producto.nombre}
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label>Cantidad Disponible:</label>
                <input type="number" name="cantidad_disponible" value="${inventario.cantidad_disponible}" 
                       min="0" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Cantidad Mínima:</label>
                <input type="number" name="cantidad_minima" value="${inventario.cantidad_minima}" 
                       min="0" class="form-control" required>
            </div>
            
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="${pageContext.request.contextPath}/inventario" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
    
    <%@include file="../../WEB-INF/templates/footer.jsp" %>
</body>
</html>
